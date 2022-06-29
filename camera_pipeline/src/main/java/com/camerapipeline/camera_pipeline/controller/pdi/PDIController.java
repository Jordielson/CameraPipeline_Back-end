package com.camerapipeline.camera_pipeline.controller.pdi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camerapipeline.camera_pipeline.controller.ControllerAbstract;
import com.camerapipeline.camera_pipeline.dto.pdi.PdiDTO;
import com.camerapipeline.camera_pipeline.mapper.pdi.PdiMapper;
import com.camerapipeline.camera_pipeline.model.pdi.PDI;
import com.camerapipeline.camera_pipeline.services.pdi.PDIService;

@RestController
@RequestMapping("/pdi")
public class PDIController extends ControllerAbstract<PDI, PdiDTO, Integer> {
    public PDIController(PDIService service, PdiMapper mapper) {
        super(service, mapper);
    }
}
