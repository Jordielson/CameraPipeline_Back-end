package com.camerapipeline.camera_pipeline.provider.mapper.pipeline;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.model.entities.input.PipelineInput;
import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.presentation.dto.input.PipelineInputDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.pdi.PdiDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.pipeline.PipelineDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.provider.mapper.input.InputMapper;
import com.camerapipeline.camera_pipeline.provider.mapper.pdi.PdiMapper;

@Component
public class PipelineMapper extends Mapper<Pipeline, PipelineDTO>{
    @Autowired
    PdiMapper pdiMapper;
    @Autowired
    InputMapper inputMapper;

    @Override
    public PipelineDTO toDTO(Pipeline model) {
        TypeMap<Pipeline, PipelineDTO> typeMap = getTypeMap(
            Pipeline.class, 
            PipelineDTO.class
        );
        Converter<List<PDI>, List<PdiDTO>> converterPDIList =
            ctx -> ctx.getSource() == null ? null : pdiMapper.toDTOList(ctx.getSource());
        Converter<List<PipelineInput>, List<PipelineInputDTO>> converterCameraList =
            ctx -> ctx.getSource() == null ? null : inputMapper.toDTOList(ctx.getSource());
        typeMap.addMappings(
            mapper -> {
                mapper.using(converterPDIList)
                    .map(
                        Pipeline::getPDIList,
                        PipelineDTO::setPDIList
                    );
                mapper.using(converterCameraList)
                    .map(
                        Pipeline::getInputList, 
                        PipelineDTO::setInputList
                    );
            }
        );
        PipelineDTO dto = modelMapper.map(
            model, 
            PipelineDTO.class
        );
        return dto;
    }

    @Override
    public Pipeline fromDTO(PipelineDTO dto) {
        TypeMap<PipelineDTO, Pipeline> typeMap = getTypeMap(
            PipelineDTO.class, 
            Pipeline.class
        );
        Converter<List<PdiDTO>, List<PDI>> converterPDIList =
            ctx -> ctx.getSource() == null ? null : pdiMapper.fromDTOList(ctx.getSource());
        Converter<List<PipelineInputDTO>, List<PipelineInput>> converterCameraList =
            ctx -> ctx.getSource() == null ? null : inputMapper.fromDTOList(ctx.getSource());
        typeMap.addMappings(
            mapper -> {
                mapper.using(converterPDIList)
                    .map(
                        PipelineDTO::getPDIList,
                        Pipeline::setPDIList
                    );
                mapper.using(converterCameraList)
                    .map(
                        PipelineDTO::getInputList,
                        Pipeline::setInputList
                    );
            }
        );
        Pipeline model = modelMapper.map(
            dto, 
            Pipeline.class
        );
        return model;
    }
}
