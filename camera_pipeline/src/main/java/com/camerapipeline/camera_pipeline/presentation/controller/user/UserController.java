package com.camerapipeline.camera_pipeline.presentation.controller.user;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.camerapipeline.camera_pipeline.model.entities.user.User;
import com.camerapipeline.camera_pipeline.presentation.dto.user.ForgotPasswordDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.user.UserResponse;
import com.camerapipeline.camera_pipeline.presentation.dto.user.UserResquest;
import com.camerapipeline.camera_pipeline.provider.services.mail.EmailService;
import com.camerapipeline.camera_pipeline.provider.services.user.UserService;


@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private EmailService emailService;

    @PostMapping("/password")
    public ResponseEntity<UserResponse> changePassword(@RequestParam("oldpassword") String oldPassword, @RequestParam("newpassword") String newPassword, Principal principal) {
        return ResponseEntity.ok(
            parseUserResponse(
                this.userService.changePassword(
                    oldPassword, 
                    newPassword, 
                    principal
                )
            )
        );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(
            parseUserResponse(this.userService.getById(id))
        );
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserResquest u) {
        User user = this.userService.create(u);
        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(parseUserResponse(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Integer id, @Valid @RequestBody User u, Principal principal) {
        User user = this.userService.update(id, u, principal);
        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).body(parseUserResponse(user));
    }

    @DeleteMapping("/{id}")
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
    }
    

    private UserResponse parseUserResponse(User user) {
        List<String> roles = user.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
        UserResponse response = new UserResponse(
            user.getId(),
            user.getEmail(),
            roles
        );
        return response;
    }
}
