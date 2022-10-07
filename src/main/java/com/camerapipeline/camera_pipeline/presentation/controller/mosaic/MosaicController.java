package com.camerapipeline.camera_pipeline.presentation.controller.mosaic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camerapipeline.camera_pipeline.model.entities.mosaic.Mosaic;
import com.camerapipeline.camera_pipeline.presentation.controller.ControllerAbstract;
import com.camerapipeline.camera_pipeline.presentation.dto.mosaic.MosaicDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.mosaic.MosaicMapper;
import com.camerapipeline.camera_pipeline.provider.services.mosaic.MosaicService;

@RestController
@RequestMapping("/mosaic")
public class MosaicController extends ControllerAbstract<Mosaic, MosaicDTO, Integer> {

    public MosaicController(MosaicService service, MosaicMapper mapper) {
        super(service, mapper);
    }
    
}
