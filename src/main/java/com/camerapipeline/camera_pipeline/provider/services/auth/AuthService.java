package com.camerapipeline.camera_pipeline.provider.services.auth;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camerapipeline.camera_pipeline.core.security.config.TokenProvider;
import com.camerapipeline.camera_pipeline.model.entities.user.User;
import com.camerapipeline.camera_pipeline.model.repository.user.UserRepository;
import com.camerapipeline.camera_pipeline.presentation.dto.user.UserDTO;

@Service
public class AuthService {
    @Autowired
    UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private TokenProvider tokenProvider;
    
    public Authentication authenticateUser(String login, String password) {
    	final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(login, password));
    	
    	return authentication;
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
    
    public User loadUserByUsername(String name) {
        return repository.findByEmail(name).get();
    }

    public boolean isValid(String token) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = 
                (UserDetails) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();
            return tokenProvider.validateToken(token, userDetails);
        }
        return false;
    }

    public Optional<Date> getDateTimeExpiration(String token) {
        return tokenProvider.getExpirationDateFromToken(token);
    }
}