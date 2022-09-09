package com.camerapipeline.camera_pipeline.presentation.controller.pdi;

import org.springframework.web.bind.annotation.RequestMapping;
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
}
