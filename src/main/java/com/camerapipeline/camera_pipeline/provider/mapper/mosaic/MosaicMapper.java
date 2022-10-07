package com.camerapipeline.camera_pipeline.provider.mapper.mosaic;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.model.entities.input.camera.Camera;
import com.camerapipeline.camera_pipeline.model.entities.mosaic.Mosaic;
import com.camerapipeline.camera_pipeline.presentation.dto.input.camera.CameraDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.mosaic.MosaicDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.provider.mapper.input.camera.CameraMapper;

@Component
public class MosaicMapper extends Mapper<Mosaic, MosaicDTO> {
    @Autowired
    CameraMapper camMapper;

    @Override
    public MosaicDTO toDTO(Mosaic model) {
        TypeMap<Mosaic, MosaicDTO> typeMap = getTypeMap(
            Mosaic.class, 
            MosaicDTO.class
        );
        Converter<List<Camera>, List<CameraDTO>> converterCamList =
            ctx -> ctx.getSource() == null ? null : camMapper.toDTOList(ctx.getSource());

        typeMap.addMappings(
            mapper -> {
                mapper.using(converterCamList)
                    .map(
                        Mosaic::getMosaicPipelineList,
                        MosaicDTO::setCameraList
                    );
            }
        );
        MosaicDTO dto = modelMapper.map(
            model, 
            MosaicDTO.class
        );
        return dto;
    }

    @Override
    public Mosaic fromDTO(MosaicDTO dto) {
        TypeMap<MosaicDTO, Mosaic> typeMap = getTypeMap(
            MosaicDTO.class, 
            Mosaic.class
        );
        Converter<List<CameraDTO>, List<Camera>> converterCamList =
            ctx -> ctx.getSource() == null ? null : camMapper.fromDTOList(ctx.getSource());
        
        typeMap.addMappings(
            mapper -> {
                mapper.using(converterCamList)
                    .map(
                        MosaicDTO::getCameraList,
                        Mosaic::setMosaicPipelineList
                    );
            }
        );
        Mosaic model = modelMapper.map(
            dto, 
            Mosaic.class
        );
        return model;
    }
    
}
