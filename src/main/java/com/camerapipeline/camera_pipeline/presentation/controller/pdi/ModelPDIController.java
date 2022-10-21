package com.camerapipeline.camera_pipeline.presentation.controller.pdi;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.camerapipeline.camera_pipeline.core.handlers.exception.ExceptionMessage;
import com.camerapipeline.camera_pipeline.model.entities.pdi.ModelPDI;
import com.camerapipeline.camera_pipeline.presentation.controller.ControllerAbstract;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.modelpdi.ModelPdiDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.shared.ValidDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.pdi.ModelPDIMapper;
import com.camerapipeline.camera_pipeline.provider.services.pdi.ModelPDIService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/model-pdi")
public class ModelPDIController extends ControllerAbstract<ModelPDI, ModelPdiDTO, Integer> {
    public ModelPDIController(ModelPDIService service, ModelPDIMapper mapper) {
        super(service, mapper);
    }

    @Operation(summary = "Search model pdi by name")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully search"),
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
        @ApiResponse(responseCode = "404", description = "Model PDI with id supplied not found", 
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
	public ResponseEntity<Page<ModelPdiDTO>> search(
			Principal principal,
            @Parameter(
                name = "name",
                description = "Query to be search",
                example = "redi",
                required = true
            )
			@RequestParam String name,
			@ParameterObject Pageable pageable) {
        ModelPdiDTO search = new ModelPdiDTO();
        search.setName(name);
        Page<ModelPdiDTO> list = mapper.toDTOPage(
            service.search(
                pageable, 
                principal, 
                mapper.fromDTO(search)
            )
        );
		
		return new ResponseEntity<Page<ModelPdiDTO>>(list, HttpStatus.OK);
	}

    @Operation(summary = "Checks if the model PDI name has already been registered")
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
    @GetMapping(value = "/verify-name", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> verifyName(
        Principal principal,
        @Parameter(
            name = "name",
            description = "Name to be verified",
            example = "PDI 05",
            required = true
        )
        @RequestParam String name,
        @Parameter(
            name = "id",
            description = "ModelPDI ID to be verified",
            example = "3",
            required = false
        )
        @RequestParam(required = false) Integer id
        ) {

        Map<String, Boolean> response = new HashMap<>();
        response.put(
            "valid", 
            ((ModelPDIService) service).checkValidName(name, id, principal)
        );
		
		return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.OK);
	}

    @Operation(summary = "Checks if the model PDI URL has already been registered")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "URL verified", 
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
    @GetMapping(value = "/verify-url", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> verifyUrl(
        Principal principal,
        @Parameter(
            name = "url",
            description = "URL to check if being verified",
            example = "https://modelpdi-server",
            required = true
        )
        @RequestParam String url,
        @Parameter(
            name = "id",
            description = "ModelPDI ID to check if being verified",
            example = "25",
            required = false
        )
        @RequestParam(required = false) Integer id
        ) {
        Map<String, Boolean> response = new HashMap<>();
        response.put(
            "valid", 
            ((ModelPDIService) service).checkValidUrl(url, id, principal)
        );
		
		return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.OK);
	}
    
    @Operation(summary = "Checks if the model PDI is being used in a pipeline")
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
    @GetMapping(value = "/verify-used", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> verifyUsed(
        Principal principal,
        @Parameter(
            name = "id",
            description = "ModelPDI ID to check if being used",
            example = "4",
            required = true
        )
        @RequestParam Integer id
        ) {
        Map<String, Boolean> response = new HashMap<>();
        response.put(
            "valid", 
            ((ModelPDIService) service).checkIfItUsed(id, principal)
        );
		
		return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.OK);
	}
}
