package com.camerapipeline.camera_pipeline.presentation.controller.auth;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.camerapipeline.camera_pipeline.core.handlers.exception.ExceptionMessage;
import com.camerapipeline.camera_pipeline.presentation.dto.auth.LoginDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.auth.ValidTokenDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.auth.ValidTokenResponse;
import com.camerapipeline.camera_pipeline.presentation.dto.user.UserDTO;
import com.camerapipeline.camera_pipeline.provider.services.auth.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class AuthController {
	@Autowired
	private AuthService authService;

	@Operation(summary = "Login")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Logged"),
        @ApiResponse(responseCode = "400", description = "Invalid email or password supplied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
    @PostMapping(value = "/login")
    public ResponseEntity<UserDTO> authenticateUser(
		@Valid @RequestBody LoginDTO loginRequest) {
        UserDTO userDetails = authService
        .authenticateUserAndGetToken(
            loginRequest.getLogin(), 
            loginRequest.getPassword()
        );

        return ResponseEntity.ok(
            userDetails
        );
    }
	
	@Operation(summary = "Check if the token is valid")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Verified token"),
        @ApiResponse(responseCode = "400", description = "Invalid email or password supplied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
	@PostMapping("/isValidToken")
	public ResponseEntity<ValidTokenResponse> isTokenValid(
		@RequestBody ValidTokenDTO dto ){
		
		boolean isValid = authService.isValid(dto.getToken());
		Optional<Date> expOptional = authService.getDateTimeExpiration(dto.getToken());

		ValidTokenResponse response = new ValidTokenResponse();
		LocalDateTime dateTimeExp = expOptional.get().toInstant()
			.atZone(ZoneId.systemDefault())
			.toLocalDateTime();
		response.setExpirationTime(dateTimeExp);
		response.setToken(dto.getToken());
		response.setIsValid(isValid);
		return ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Logout")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Logout"),
        @ApiResponse(responseCode = "400", description = "No session to end", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
	@PostMapping(value = "/logout")
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
