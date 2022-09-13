package com.camerapipeline.camera_pipeline.services.user;

import com.camerapipeline.camera_pipeline.core.security.config.TokenProvider;
import com.camerapipeline.camera_pipeline.dto.user.UserDTO;
import com.camerapipeline.camera_pipeline.exception.geral.RegraDeNegocioException;
import com.camerapipeline.camera_pipeline.exception.user.UserNotFoundException;
import com.camerapipeline.camera_pipeline.model.user.User;
import com.camerapipeline.camera_pipeline.repository.user.UserRepository;
import com.camerapipeline.camera_pipeline.services.ServiceAbstract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService extends ServiceAbstract<User, Integer> {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public UserService(UserRepository repository) {
        super(repository);
    }
    
    public UserDTO authenticateUserAndGetToken(String login, String password) {
        final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(login, password));
                
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();	
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
        
        UserDTO userDto = new UserDTO(
            jwt,
            "Bearer",
            userDetails.getUsername(),
            roles
        );

        return userDto;
    }

    public User changePassword(String oldPassword, String newPassword, Principal principal) {

        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(principal.getName(), oldPassword));

        if (authentication.isAuthenticated()) {
            return ((UserRepository) super.repository).findByEmail(principal.getName()).map(existing -> {
                existing.setPassword(passwordEncoder.encode(newPassword));
                return this.repository.save(existing);
            }).orElseThrow(SecurityException::new);
        }
        throw new SecurityException();
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
    
    /**
     * TODO Metodo para recuperar usuario por email
     * @return
     */
    public User recuperarUsuarioPorEmail(String email) {
    		
    	Optional<User> userReturn = ((UserRepository) super.repository).findByEmail(email);
    	
    	if(userReturn.isPresent()) {
    		return userReturn.get();
    	}
    	
    	throw new RegraDeNegocioException("Nenhum usuario cadastrado com este endereço de Email");
		
    }
}
