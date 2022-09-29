package com.camerapipeline.camera_pipeline.presentation.controller.camera;

import java.security.Principal;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.camerapipeline.camera_pipeline.core.handlers.exception.ExceptionMessage;
import com.camerapipeline.camera_pipeline.model.entities.camera.Camera;
import com.camerapipeline.camera_pipeline.presentation.controller.ControllerAbstract;
import com.camerapipeline.camera_pipeline.presentation.dto.camera.CameraDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.shared.ValidDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.provider.services.camera.CameraService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/camera")
public class CameraController extends ControllerAbstract<Camera, CameraDTO, Integer>{
    public CameraController(CameraService service, Mapper<Camera, CameraDTO> mapper) {
        super(service, mapper);
    }

    @Operation(summary = "Search camera by name")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully search"),
        @ApiResponse(responseCode = "400", description = "Invalid name supplied", 
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
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<CameraDTO>> search(
			Principal principal,
			@RequestParam String name,
			@ParameterObject Pageable pageable) {
        CameraDTO search = new CameraDTO().name(name);
        Page<CameraDTO> list = mapper.toDTOPage(
            service.search(
                pageable, 
                principal, 
                mapper.fromDTO(search)
            )
        );
		
		return new ResponseEntity<Page<CameraDTO>>(list, HttpStatus.OK);
	}

    @Operation(summary = "Checks if the camera name has already been registered")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Name verified", 
            content = { 
                @Content(mediaType = "application/json", 
                    schema = @Schema( implementation = ValidDTO.class)
                ) 
            }
        ),
        @ApiResponse(responseCode = "400", description = "Invalid name supplied", 
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
    @GetMapping("/verify-name")
	public ResponseEntity<ValidDTO> verifyName(
        Principal principal,
        @RequestParam String name,
        @RequestParam(required = false) Integer id
        ) {

        ValidDTO response = new ValidDTO(
            ((CameraService) service).checkValidName(name, id, principal)
        );
		
		return new ResponseEntity<ValidDTO>(response, HttpStatus.OK);
	}

    @Operation(summary = "Checks if the camera link has already been registered")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Link verified", 
            content = { 
                @Content(mediaType = "application/json", 
                    schema = @Schema( implementation = ValidDTO.class)
                ) 
            }
        ),
        @ApiResponse(responseCode = "400", description = "Invalid URL supplied", 
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
    @GetMapping("/verify-url")
	public ResponseEntity<ValidDTO> verifyUrl(
        Principal principal,
        @RequestParam String url,
        @RequestParam(required = false) Integer id
        ) {
        ValidDTO response = new ValidDTO(
            ((CameraService) service).checkValidUrl(url, id, principal)
        );
		
		return new ResponseEntity<ValidDTO>(response, HttpStatus.OK);
	}

    @Operation(summary = "Checks if the camera is being used in a pipeline")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully verified", 
            content = { 
                @Content(mediaType = "application/json", 
                    schema = @Schema( implementation = ValidDTO.class)
                ) 
            }
        ),
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
    @GetMapping("/verify-used")
	public ResponseEntity<ValidDTO> verifyUsed(
        Principal principal,
        @RequestParam Integer id
        ) {
        ValidDTO response = new ValidDTO(
            ((CameraService) service).checkIfItUsed(id, principal)
        );
		
		return new ResponseEntity<ValidDTO>(response, HttpStatus.OK);
	}

    @Operation(summary = "Enable or disable a camera's functionality")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Camera on or off", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = CameraDTO.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid id or active supplied", 
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
        @ApiResponse(responseCode = "404", description = "Camera not found", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
    @PatchMapping("/{id}/state")
	public ResponseEntity<CameraDTO> setStatus(
        Principal principal,
        @PathVariable("id") Integer id,
        @RequestParam(name="active", required=true) Boolean active) {
        CameraDTO response = mapper.toDTO(
            ((CameraService) service)
                .setActive(id, active, principal)
        );

        return new ResponseEntity<>(
            response,
            HttpStatus.OK
        );
    }
}
