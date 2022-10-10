package com.camerapipeline.camera_pipeline.unitario;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.Principal;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.camerapipeline.camera_pipeline.CameraPipelineApplication;
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
    private AuthService authService;
	
	Pageable pageable = Pageable.unpaged();
	
	private User user;
	
	private Principal principal = null;
	
	@BeforeEach
	public void preConfig() {
		
		principal = authService.authenticateUser("admin@admin.com", "123456");
		user = montarUser("UserTestUnit@user.com", "123456");

		limparBanco();
		
	}
	
	/** sessão de sucesso **/
	
	@Test
	public void testeSalvarUserComSucesso() {
		
		//Dado
		assertThrows(EntityNotFoundException.class, () -> {
			userService.getByEmail(user.getEmail());
		});
		
		//Quando 
		User userReturn = userService.create(user);
		
		//Entao
		assertNotNull(userService.getByEmail(userReturn.getEmail()));
		assertEquals(userService.getByEmail(userReturn.getEmail()).getEmail(), user.getEmail());
	
	}
	
	@Test
	public void testeAltararSenhaUserComSucesso() {
		
		//Dado
		User novoUser = userService.create(user);
		principal = authService.authenticateUser("UserTestUnit@user.com", "123456");
		
		String novaSenha = "654321";
		
		//Quando 
		User userAlterado = userService.changePassword("123456", novaSenha , principal);
		
		//Entao
		assertFalse(userAlterado.getPassword().equals(novoUser.getPassword()));
	}
	
	@Test
	public void testeDeletarUserComSucesso() {
		
		//Dado
		User novoUser = userService.create(user);
		principal = authService.authenticateUser("UserTestUnit@user.com", "123456");
		assertNotNull(userService.getByEmail(user.getEmail()));
		
		//Quando
		userService.delete(novoUser.getId(), principal);
		Optional<User> preUser = userRepository.findByEmail(user.getEmail());
		
		//Entao
		assertTrue(preUser.isEmpty());
		
		
	}
	
	/** sessão de falha **/
	
	@Test
	public void testeErroFormatoDeEmailInvalido() {
		
		User userTest = new User();
		userTest.setEmail("emailInvalido");
		userTest.setPassword("123456");
		
		assertThrows(ConstraintViolationException.class, () -> {
			userService.create(userTest);
		});
		
	}
	
	@Test
	public void testeErroCriarUserComEmailExistente() {
		
		User novoUser = userService.create(user);
		
		assertThrows(DataIntegrityViolationException.class, () -> {
			userService.create(montarUser(novoUser.getEmail(), "123456"));
		});
		
	}
	
	public void limparBanco() {
		Optional<User> preUser = userRepository.findByEmail(user.getEmail());
		if(preUser.isPresent()) {
			userRepository.deleteById(user.getId());
		}
		
	}
	
	private User montarUser(String email, String senha) {
		User novoUser = new User();
		novoUser.setEmail(email);
		novoUser.setPassword(senha);
		return novoUser;
	}

	@AfterEach
	public void posConfig() {
		limparBanco();
	}
	
}