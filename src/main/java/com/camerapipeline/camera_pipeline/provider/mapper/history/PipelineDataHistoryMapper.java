package com.camerapipeline.camera_pipeline.provider.mapper.history;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.model.entities.history.PdiDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.history.PipelineDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.presentation.dto.history.PipelineDataHistoryDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.pdi.PdiDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.provider.mapper.pdi.PdiMapper;

@Component
public class PipelineDataHistoryMapper extends Mapper<PipelineDataHistory, PipelineDataHistoryDTO>{
    @Autowired
    PdiDataHistoryMapper pdiMapper;
    @Autowired
    PdiMapper pdiMapper2;

    @Override
    public PipelineDataHistoryDTO toDTO(PipelineDataHistory model) {
        TypeMap<PipelineDataHistory, PipelineDataHistoryDTO> typeMap = getTypeMap(
            PipelineDataHistory.class, 
            PipelineDataHistoryDTO.class
        );
        Converter<List<PdiDataHistory>, List<PdiDTO>> converterPDIList =
            ctx -> ctx.getSource() == null ? null : pdiMapper.toDTOList(ctx.getSource());

        typeMap.addMappings(
            mapper -> {
                mapper.using(converterPDIList)
                    .map(
                        PipelineDataHistory::getPDIList,
                        PipelineDataHistoryDTO::setPDIList
                    );
            }
        );
        PipelineDataHistoryDTO dto = modelMapper.map(
            model, 
            PipelineDataHistoryDTO.class
        );
        return dto;
    }
    
    @Override
    public PipelineDataHistory fromDTO(PipelineDataHistoryDTO dto) {
        return null;
    }

    public Pipeline convertToPipeline(PipelineDataHistory history) {
        Pipeline pipeline = new Pipeline();
        
        pipeline.setId(history.getId());
        pipeline.setActive(history.isActive());
        pipeline.setDescription(history.getDescription());
        pipeline.setName(history.getName());
        pipeline.setPDIList(
            pdiMapper2.fromDTOList(
                pdiMapper.toDTOList(
                    history.getPDIList()
                )
            )
        );
        pipeline.setUser(history.getUser());
        return pipeline;
    }
}
