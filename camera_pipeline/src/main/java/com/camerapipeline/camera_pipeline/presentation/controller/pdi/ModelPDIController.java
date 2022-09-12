package com.camerapipeline.camera_pipeline.presentation.controller.pdi;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.camerapipeline.camera_pipeline.model.entities.pdi.ModelPDI;
import com.camerapipeline.camera_pipeline.presentation.controller.ControllerAbstract;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.ModelPdiDTO;
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
        List<ModelPdiDTO> list = mapper.toDTOList(
            service.search(
                pageable, 
                principal, 
                mapper.fromDTO(search)
            ).toList()
        );
		
		return new ResponseEntity<List<ModelPdiDTO>>(list, HttpStatus.OK);
	}
}
