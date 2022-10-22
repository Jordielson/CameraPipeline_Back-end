package com.camerapipeline.camera_pipeline.provider.mapper.pipeline;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.model.enums.Category;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.pdi.PdiDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.pipeline.PipelineDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.provider.mapper.pdi.PdiMapper;

@Component
public class PipelineMapper extends Mapper<Pipeline, PipelineDTO>{
    @Autowired
    PdiMapper pdiMapper;

    @Override
    public PipelineDTO toDTO(Pipeline model) {
        TypeMap<Pipeline, PipelineDTO> typeMap = getTypeMap(
            Pipeline.class, 
            PipelineDTO.class
        );
        Converter<List<PDI>, List<PdiDTO>> converterPDIList =
            ctx -> ctx.getSource() == null ? null : pdiMapper.toDTOList(ctx.getSource());

        typeMap.addMappings(
            mapper -> {
                mapper.using(converterPDIList)
                    .map(
                        Pipeline::getPDIList,
                        PipelineDTO::setPDIList
                    );
            }
        );
        PipelineDTO dto = modelMapper.map(
            model, 
            PipelineDTO.class
        );

        dto.setCategory(Category.PIPELINE);
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
        
        typeMap.addMappings(
            mapper -> {
                mapper.using(converterPDIList)
                    .map(
                        PipelineDTO::getPDIList,
                        Pipeline::setPDIList
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
