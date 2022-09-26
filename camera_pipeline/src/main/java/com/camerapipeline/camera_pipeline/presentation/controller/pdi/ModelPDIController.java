package com.camerapipeline.camera_pipeline.presentation.controller.pdi;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.camerapipeline.camera_pipeline.model.entities.pdi.ModelPDI;
import com.camerapipeline.camera_pipeline.presentation.controller.ControllerAbstract;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.modelpdi.ModelPdiDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.pdi.ModelPDIMapper;
import com.camerapipeline.camera_pipeline.provider.services.pdi.ModelPDIService;

@RestController
@RequestMapping("/model-pdi")
public class ModelPDIController extends ControllerAbstract<ModelPDI, ModelPdiDTO, Integer> {
    public ModelPDIController(ModelPDIService service, ModelPDIMapper mapper) {
        super(service, mapper);
    }

    @GetMapping
	public ResponseEntity<?> search(
			Principal principal,
			@RequestParam String name,
			Pageable pageable) {
        ModelPdiDTO search = new ModelPdiDTO().name(name);
        Page<ModelPdiDTO> list = mapper.toDTOPage(
            service.search(
                pageable, 
                principal, 
                mapper.fromDTO(search)
            )
        );
		
		return new ResponseEntity<Page<ModelPdiDTO>>(list, HttpStatus.OK);
	}

    @GetMapping("/verify-name")
	public ResponseEntity<?> verifyName(
        Principal principal,
        @RequestParam String name,
        @RequestParam(required = false) Integer id
        ) {

        Map<String, Boolean> response = new HashMap<>();
        response.put(
            "valid", 
            ((ModelPDIService) service).checkValidName(name, id, principal)
        );
		
		return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.OK);
	}

    @GetMapping("/verify-url")
	public ResponseEntity<?> verifyUrl(
        Principal principal,
        @RequestParam String url,
        @RequestParam(required = false) Integer id
        ) {
        Map<String, Boolean> response = new HashMap<>();
        response.put(
            "valid", 
            ((ModelPDIService) service).checkValidUrl(url, id, principal)
        );
		
		return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.OK);
	}
    
    @GetMapping("/verify-used")
	public ResponseEntity<?> verifyUsed(
        Principal principal,
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
