package com.camerapipeline.camera_pipeline.provider.services.user;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.core.security.config.JwtConfig;
import com.camerapipeline.camera_pipeline.core.security.config.TokenProvider;
import com.camerapipeline.camera_pipeline.model.entities.user.Role;
import com.camerapipeline.camera_pipeline.model.entities.user.User;
import com.camerapipeline.camera_pipeline.model.repository.user.UserRepository;
import com.camerapipeline.camera_pipeline.presentation.dto.user.UserResquest;
import com.camerapipeline.camera_pipeline.provider.exception.CustomEntityNotFoundException;
import com.camerapipeline.camera_pipeline.provider.exception.user.SamePasswordException;
import com.camerapipeline.camera_pipeline.provider.exception.user.UserNotFoundException;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;
import com.camerapipeline.camera_pipeline.provider.services.mail.EmailService;
import com.camerapipeline.camera_pipeline.provider.specification.user.UserSpecification;

@Service
public class UserService extends ServiceAbstract<User, Integer> {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JwtConfig jwtConfig;
    
    public UserService(UserRepository repository) {
        super(repository);
    }

    public User changePassword(String oldPassword, String newPassword, Principal principal) {

        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(principal.getName(), oldPassword));

        if (authentication.isAuthenticated()) {
            return ((UserRepository) super.repository).findByEmail(principal.getName()).map(existing -> {
                checkPasswordSameOld(newPassword, existing);
                existing.setPassword(passwordEncoder.encode(newPassword));
                return this.repository.save(existing);
            }).orElseThrow(SecurityException::new);
        }
        throw new SecurityException();
    }

    public User create(UserResquest u) {
        User user = new User()
            .email(u.getEmail())
            .password(
                passwordEncoder.encode(u.getPassword())
            );

        user.setRoles(List.of(new Role("ROLE_USER")));
        
        return super.create(user);
    }

    @Override
    public User create(User u) {
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return super.create(u);
    }

    @Override
    public User update(Integer id, User u, Principal principal) {
        return super.repository.findById(id)
                .map(existing -> {
                    u.setId(id);
                    u.setPassword(existing.getPassword());
                    return super.repository.save(u);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    private void checkPasswordSameOld(String newPassword, User user) {
        if(passwordEncoder.matches(newPassword, user.getPassword())) {
            throw new SamePasswordException("Password is the same as old password", user.getId());
        }
    }
    
    /**
     * Metodo para recuperar usuario por email
     * @return
     */
    public User getByEmail(String email) {
    	Optional<User> userReturn = ((UserRepository) super.repository).findByEmail(email);
    	
    	if(userReturn.isPresent()) {
    		return userReturn.get();
    	}
    	
    	throw new CustomEntityNotFoundException("User", email);
    }

    public void forgotPassword(String email, String link) {
        User user = getByEmail(email); 
        emailService.sendEmail(
            emailService.preShapedEmail(
                "recovery mail - " + user.getUsername(),
                email, 
                "Email de Recuperação de Senha",
                recoveryEmailContent(addTokenOnLink(user, link)),
                user.getUsername()
            )
        );
    }

    private String addTokenOnLink(User user, String link) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(
            "AUTHORITIES_KEY", 
            user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","))
        );
        claims.put("PASSWORD_KEY", user.getPassword());

        jwtConfig.setTokenValidityInSeconds(10800);
        TokenProvider provider = new TokenProvider(jwtConfig);
        String token = provider.generateToken(user.getEmail(), claims);

        return String.format("%s/%s", link, token);
    }

    public User passwordReset(String token, String password) {
        TokenProvider provider = new TokenProvider(jwtConfig);
        Optional<String> username = provider.getUsernameFromToken(token);
        
        if (provider.isTokenExpired(token)) {
            throw new CredentialsExpiredException("Expired token");
        }else if (username.isPresent()) {
            return ((UserRepository) super.repository)
                .findByEmail(username.get()).map(existing -> {
                    Optional<String> oldPassword = provider.getClaimFromToken(token, "PASSWORD_KEY");
                    if (oldPassword.isPresent() && existing.getPassword().equals(oldPassword.get())) {
                        checkPasswordSameOld(password, existing);
                        existing.setPassword(passwordEncoder.encode(password));
                        return this.repository.save(existing);
                    } else {
                        throw new CredentialsExpiredException("User not found");
                    }
                }).orElseThrow(SecurityException::new);
        } else {
            throw new CredentialsExpiredException("Username is not found");
        }
    }

    private String recoveryEmailContent(String link) {
    	return "<H1> Camera_Pipeline <H1>"
            + "<H3>Você solicitou a redefinição de sua senha.</H3>"
            + "<H3>Clique no link abaixo para alterar sua senha:</H3>"
            + "<H3><a href=\"" + link + "\">Mudar minha senha</a></H3>"
            + "<br>"
            + "<H3>Ignore este e-mail se você se lembrar de sua senha.</H3>"
            + "<H3>Se não foi você que solicitou a recuperação de senha ignore"
            + " este email e não compartilhe esse link com ninguém!</H3>";
    }

    @Override
    protected Specification<User> getSpecification(User search) {
        return new UserSpecification(search);
    }

    @Override
    protected EntityNotFoundException throwNotFoundEntity(Integer id) {
        return new UserNotFoundException(id);
    }
}
