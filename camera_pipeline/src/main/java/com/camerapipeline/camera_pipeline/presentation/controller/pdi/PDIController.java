package com.camerapipeline.camera_pipeline.presentation.controller.pdi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;
import com.camerapipeline.camera_pipeline.presentation.controller.ControllerAbstract;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.pdi.PdiDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.pdi.PdiMapper;
import com.camerapipeline.camera_pipeline.provider.services.pdi.PDIService;

@RestController
@RequestMapping("/pdi")
public class PDIController extends ControllerAbstract<PDI, PdiDTO, Integer> {
    public PDIController(PDIService service, PdiMapper mapper) {
        super(service, mapper);
    }
}
