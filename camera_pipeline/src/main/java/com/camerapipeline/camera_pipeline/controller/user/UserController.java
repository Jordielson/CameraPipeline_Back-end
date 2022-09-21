package com.camerapipeline.camera_pipeline.controller.user;

import java.net.URI;
import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.camerapipeline.camera_pipeline.dto.user.ForgotPasswordDTO;
import com.camerapipeline.camera_pipeline.dto.user.LoginDTO;
import com.camerapipeline.camera_pipeline.dto.user.UserDTO;
import com.camerapipeline.camera_pipeline.exception.geral.RegraDeNegocioException;
import com.camerapipeline.camera_pipeline.model.user.User;
import com.camerapipeline.camera_pipeline.services.email.EmailService;
import com.camerapipeline.camera_pipeline.services.user.UserService;


@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {
        System.out.println("Login: " + loginRequest.getLogin());
        System.out.println("Senha: " + loginRequest.getPassword());
        UserDTO userDetails = userService
        .authenticateUserAndGetToken(
            loginRequest.getLogin(), 
            loginRequest.getPassword()
        );

        return ResponseEntity.ok(
            userDetails
        );
    }

    @PostMapping("/password")
    public ResponseEntity<User> changePassword(@RequestParam("oldpassword") String oldPassword, @RequestParam("newpassword") String newPassword, Principal principal) {
        return ResponseEntity.ok(
            this.userService
                .changePassword(
                    oldPassword, 
                    newPassword, 
                    principal
                )
        );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(
            this.userService.getById(id)
        );
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> createUser(@RequestBody @Valid User u) {
        User user = this.userService.create(u);
        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @Valid @RequestBody User u, Principal principal) {
        User user = this.userService.update(id, u, principal);
        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).body(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id, Principal principal) {
        this.userService.delete(id, principal);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * TODO Recuperar Senha
     * Metodo temporario para testar o envio de email para recuperação de senha
     */

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody @Valid ForgotPasswordDTO recoveryEmail ) {
    	try {
    		User user = userService.getByEmail(recoveryEmail.getEmail()); 
    		emailService.sendEmail(
                emailService.preShapedEmail(
                    "recovery mail - "+user.getUsername(),
                    recoveryEmail.getEmail(), 
                    "Email de Recuperação de Senha",
                    // TODO Logica de troca de senha aqui! 
                    user.getPassword(),
                    user.getUsername()
                )
            );
    		
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (RegraDeNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
    
}
