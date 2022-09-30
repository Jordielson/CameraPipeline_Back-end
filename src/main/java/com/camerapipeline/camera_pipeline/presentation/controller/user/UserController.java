package com.camerapipeline.camera_pipeline.presentation.controller.user;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Size;

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

import com.camerapipeline.camera_pipeline.core.handlers.exception.ExceptionMessage;
import com.camerapipeline.camera_pipeline.model.entities.user.User;
import com.camerapipeline.camera_pipeline.presentation.dto.user.ForgotPasswordDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.user.UserResponse;
import com.camerapipeline.camera_pipeline.presentation.dto.user.UserResquest;
import com.camerapipeline.camera_pipeline.provider.services.user.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Authenticated user password change")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully change"),
        @ApiResponse(responseCode = "400", description = "Invalid params supplied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "401", description = "Unauthorized", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "403", description = "Access denied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
    @PostMapping("/password")
    public ResponseEntity<UserResponse> changePassword(
        @Parameter(
            name = "oldpassword",
            description = "Current password",
            example = "Sadm@klds.d25",
            required = true
        )
        @RequestParam("oldpassword") @Size(min = 6) String oldPassword, 
        @Parameter(
            name = "newpassword",
            description = "New user password",
            example = "Za_m@kLds.d58",
            required = true
        )
        @RequestParam("newpassword") @Size(min = 6) String newPassword, 
        Principal principal
    ) {
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

    @Operation(summary = "Get a user by its id")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully found"),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "401", description = "Unauthorized", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "403", description = "Access denied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> getUser(
        @Parameter(
            name = "id",
            description = "User ID to fetch",
            example = "652",
            required = true
        )
        @PathVariable("id") Integer id,
        Principal principal
    ) {
        return ResponseEntity.ok(
            parseUserResponse(this.userService.getById(id, principal))
        );
    }

    @Operation(summary = "Register a user")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully save"),
        @ApiResponse(responseCode = "400", description = "Invalid user supplied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "401", description = "Unauthorized", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "403", description = "Access denied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(
        @RequestBody @Valid UserResquest u) {
        User user = this.userService.create(u);
        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(parseUserResponse(user));
    }

    @Operation(summary = "Update a user")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully save"),
        @ApiResponse(responseCode = "400", description = "Invalid user supplied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "401", description = "Unauthorized", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "403", description = "Access denied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
        @Parameter(
            name = "id",
            description = "User ID to fetch",
            example = "652",
            required = true
        )
        @PathVariable("id") Integer id, 
        @Valid @RequestBody UserResquest u, 
        Principal principal) {
        User user = new User();
        user.setPassword(u.getPassword());
        user.setEmail(u.getEmail());
        User response = this.userService.update(id, user, principal);
        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).body(parseUserResponse(response));
    }

    @Operation(summary = "Delete a user")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully removed"),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "401", description = "Unauthorized", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "403", description = "Access denied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(
        @Parameter(
            name = "id",
            description = "User ID to delete",
            example = "652",
            required = true
        )
        @PathVariable("id") Integer id, 
        Principal principal) {
        this.userService.delete(id, principal);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary = "Password recovery")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Email with link to recovery successfully sent"),
        @ApiResponse(responseCode = "400", description = "Invalid email supplied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "401", description = "Unauthorized", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "403", description = "Access denied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(
        @RequestBody @Valid ForgotPasswordDTO recoveryEmail
    ) {
        userService.forgotPassword(
            recoveryEmail.getEmail(),
            recoveryEmail.getRedirect()
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

    @Operation(summary = "User password reset")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully reseted"),
        @ApiResponse(responseCode = "400", description = "Invalid token or password supplied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "401", description = "Unauthorized", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "403", description = "Access denied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
    @PostMapping("/password-reset")
    public ResponseEntity<UserResponse> resetPassword(
        @Parameter(
            name = "newpassword",
            description = "New user password",
            example = "Za_m@kLds.d58",
            required = true
        )
        @RequestParam("newpassword") @Size(min = 6) String newPassword, 
        @Parameter(
            name = "token",
            description = "Token needed for password recovery",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2FvQ"+
            "GdtYWlsLmNvbSIsInNjb3BlcyI6IlJPTEVfVVNFUiIsImlhdC"+
            "I6MTY2MjkyMzU0OSwiZXhwIjoxNjY0MTIzNTQ5fQ.NGoBjVtnURZ"+
            "x_P0t-istEJEHXDetbRZbrVYPqnaIeOU",
            required = true
        )
        @RequestParam("token") String token
    ) {
        return ResponseEntity.ok(
            parseUserResponse(
                this.userService.passwordReset(
                    token, 
                    newPassword
                )
            )
        );
    }
}
