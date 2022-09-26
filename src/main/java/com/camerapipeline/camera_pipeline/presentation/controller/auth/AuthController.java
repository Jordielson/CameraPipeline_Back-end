package com.camerapipeline.camera_pipeline.presentation.controller.auth;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.camerapipeline.camera_pipeline.presentation.dto.auth.LoginDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.auth.ValidTokenDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.auth.ValidTokenResponse;
import com.camerapipeline.camera_pipeline.presentation.dto.user.UserDTO;
import com.camerapipeline.camera_pipeline.provider.services.auth.AuthService;

@RestController
@RequestMapping
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class AuthController {
	@Autowired
	private AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<UserDTO> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {
        UserDTO userDetails = authService
        .authenticateUserAndGetToken(
            loginRequest.getLogin(), 
            loginRequest.getPassword()
        );

        return ResponseEntity.ok(
            userDetails
        );
    }
	
	@PostMapping("/isValidToken")
	public ResponseEntity<?> isTokenValid(@RequestBody ValidTokenDTO dto ){
		
		boolean isValid = authService.isValid(dto.getToken());
		Optional<Date> expOptional = authService.getDateTimeExpiration(dto.getToken());
		if (isValid && expOptional.isPresent()) {
			ValidTokenResponse response = new ValidTokenResponse();
			LocalDateTime dateTimeExp = expOptional.get().toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime();
			response.setExpirationTime(dateTimeExp);
			response.setToken(dto.getToken());
			response.setIsValid(isValid);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();		
		}
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(
			HttpSession session){
		try {
			session.invalidate();
			
			return ResponseEntity.ok("Logout completed!");
		} catch  (IllegalStateException exception) {
			return ResponseEntity.badRequest().body("There is no session!");
		}
	}
}
