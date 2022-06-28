package com.camerapipeline.camera_pipeline.mapper.pipeline;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.dto.GroupPipelineDTO;
import com.camerapipeline.camera_pipeline.dto.PipelineDTO;
import com.camerapipeline.camera_pipeline.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.model.GroupPipeline;
import com.camerapipeline.camera_pipeline.model.Pipeline;

@Component
public class GroupPipelineMapper extends Mapper<GroupPipeline, GroupPipelineDTO> {
    @Autowired
    PipelineMapper pipelineMapper;

    @Override
    public GroupPipelineDTO toDTO(GroupPipeline model) {
        TypeMap<GroupPipeline, GroupPipelineDTO> typeMap = getTypeMap(
            GroupPipeline.class, 
            GroupPipelineDTO.class
        );
        Converter<List<Pipeline>, List<PipelineDTO>> converterPipelineList = 
            ctx -> ctx.getSource() == null ? 
                null :
                pipelineMapper.toDTOList(ctx.getSource());

        typeMap.addMappings(
            mapper -> {
                mapper.using(converterPipelineList)
                    .map(
                        GroupPipeline::getPipelineList, 
                        GroupPipelineDTO::setPipelineList
                    );
            }
        );

        GroupPipelineDTO dto = modelMapper.map(
            model, 
            GroupPipelineDTO.class
        );
        return dto;
    }

    @Override
    public GroupPipeline fromDTO(GroupPipelineDTO dto) {
        TypeMap<GroupPipelineDTO, GroupPipeline> typeMap = getTypeMap(
            GroupPipelineDTO.class, 
            GroupPipeline.class
        );
        Converter<List<PipelineDTO>, List<Pipeline>> converterPipelineList = 
            ctx -> ctx.getSource() == null ? 
                null :
                pipelineMapper.fromDTOList(ctx.getSource());

        typeMap.addMappings(
            mapper -> {
                mapper.using(converterPipelineList)
                    .map(
                        GroupPipelineDTO::getPipelineList, 
                        GroupPipeline::setPipelineList
                    );
            }
        );

        GroupPipeline model = modelMapper.map(
            dto, 
            GroupPipeline.class
        );
        return model;
    }
}
