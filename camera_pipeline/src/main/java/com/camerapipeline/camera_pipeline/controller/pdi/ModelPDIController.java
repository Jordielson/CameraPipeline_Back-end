package com.camerapipeline.camera_pipeline.controller.pdi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camerapipeline.camera_pipeline.controller.ControllerAbstract;
import com.camerapipeline.camera_pipeline.dto.pdi.ModelPdiDTO;
import com.camerapipeline.camera_pipeline.mapper.pdi.ModelPDIMapper;
import com.camerapipeline.camera_pipeline.model.pdi.ModelPDI;
import com.camerapipeline.camera_pipeline.services.pdi.ModelPDIService;

@RestController
@RequestMapping("/model-pdi")
public class ModelPDIController extends ControllerAbstract<ModelPDI, ModelPdiDTO, Integer> {
    public ModelPDIController(ModelPDIService service, ModelPDIMapper mapper) {
        super(service, mapper);
    }
}
