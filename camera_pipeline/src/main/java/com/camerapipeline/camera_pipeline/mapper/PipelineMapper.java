package com.camerapipeline.camera_pipeline.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.dto.PdiDTO;
import com.camerapipeline.camera_pipeline.dto.PipelineDTO;
import com.camerapipeline.camera_pipeline.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.model.PDI;
import com.camerapipeline.camera_pipeline.model.Pipeline;

@Component
public class PipelineMapper extends Mapper<Pipeline, PipelineDTO>{
    @Autowired
    PdiMapper pdiMapper;

    public TypeMap<Pipeline, PipelineDTO> getTypeMap() {
        return super.getTypeMap(
            Pipeline.class, 
            PipelineDTO.class
        );
    }

    @Override
    public PipelineDTO toDTO(Pipeline model) {
        TypeMap<Pipeline, PipelineDTO> typeMap = getTypeMap();
        Converter<List<PDI>, List<PdiDTO>> converterPDIList =
            ctx -> ctx.getSource() == null ? null : pdiMapper.toDTOList(ctx.getSource());
        typeMap.addMappings(
            mapper -> {
                mapper.map(
                    src -> src.getGroupPipeline().getId(), 
                    PipelineDTO::setGroupPipelineId
                );
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
        return dto;
    }

    @Override
    public Pipeline fromDTO(PipelineDTO dto) {
        return null;
    }

    @Override
    public List<Pipeline> toModelList(List<PipelineDTO> modelList) {
        return null;
    }

    @Override
    public List<PipelineDTO> toDTOList(List<Pipeline> modelList) {
        List<PipelineDTO> list = new ArrayList<PipelineDTO>();
        for (Pipeline pipeline : modelList) {
            list.add(toDTO(pipeline));
        }
        return list;
    }
}
