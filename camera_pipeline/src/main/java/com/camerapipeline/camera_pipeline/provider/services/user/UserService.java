package com.camerapipeline.camera_pipeline.provider.services.user;

import com.camerapipeline.camera_pipeline.model.entities.user.User;
import com.camerapipeline.camera_pipeline.model.repository.user.UserRepository;
import com.camerapipeline.camera_pipeline.provider.exception.user.UserNotFoundException;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;
import com.camerapipeline.camera_pipeline.provider.specification.user.UserSpecification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService extends ServiceAbstract<User, Integer> {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public UserService(UserRepository repository) {
        super(repository);
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

    @Override
    protected Specification<User> getSpecification(User search) {
        return new UserSpecification(search);
    }

    @Override
    protected EntityNotFoundException throwNotFoundEntity(Integer id) {
        return new UserNotFoundException(id);
    }
}
