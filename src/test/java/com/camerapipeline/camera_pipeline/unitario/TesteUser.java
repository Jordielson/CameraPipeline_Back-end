package com.camerapipeline.camera_pipeline.unitario;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.Principal;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.camerapipeline.camera_pipeline.CameraPipelineApplication;
import com.camerapipeline.camera_pipeline.core.security.config.TokenProvider;
import com.camerapipeline.camera_pipeline.model.entities.user.Role;
import com.camerapipeline.camera_pipeline.model.entities.user.User;
import com.camerapipeline.camera_pipeline.model.repository.user.UserRepository;
import com.camerapipeline.camera_pipeline.provider.services.auth.AuthService;
import com.camerapipeline.camera_pipeline.provider.services.user.UserService;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration
@SpringBootTest(classes = CameraPipelineApplication.class)
public class TesteUser {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
    private AuthService authService;
	
	private User user = new User();
	
	private Principal principal = null;
	
	@BeforeEach
	public void preConfig() {
		
		//Dado que
		principal = authService.authenticateUser("admin@admin.com", "123456");
		
		user.setEmail("unitTest@user.com");
		user.setPassword(passwordEncoder.encode("123456"));
		user.setRoles(List.of(new Role("ROLE_USER")));
		
	}
	
	/** sessão de sucesso **/
	
	
	public void testeSalvarUserComSucesso() {
		//Quando 
		
		User userReturn = userService.create(user);
		
		//Entao
		assertNotNull(userService.getByEmail(userReturn.getEmail()));
		userRepository.delete(userReturn);
		Assertions.catchThrowableOfType(() -> userService.getByEmail(userReturn.getEmail()), Exception.class);
		
	}
	
	
}