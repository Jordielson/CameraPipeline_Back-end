package com.camerapipeline.camera_pipeline.provider.mapper.history;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.model.entities.history.PdiDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.history.ValueParameterDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.pdi.DigitalProcess;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.pdi.DigitalProcessDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.pdi.PdiDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.valueparameter.ValueParameterDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.provider.mapper.pdi.DigitalProcessMapper;
import com.camerapipeline.camera_pipeline.provider.services.pipeline.PipelineService;

@Component
public class PdiDataHistoryMapper extends Mapper<PdiDataHistory, PdiDTO>{
    @Autowired
    ValueParameterDataHistoryMapper valueParameterMapper;
    @Autowired
    DigitalProcessMapper digitalProcessMapper;
    @Autowired
    PipelineService pipelineService;

    @Override
    public PdiDTO toDTO(PdiDataHistory model) {
        Converter<List<ValueParameterDataHistory>, List<ValueParameterDTO>> converterListValueParameter =
            ctx -> ctx.getSource() == null ? 
                null : 
                valueParameterMapper.toDTOList(
                    ctx.getSource()
                );
        Converter<DigitalProcess, DigitalProcessDTO> converterDigitalProcess =
            ctx -> ctx.getSource() == null ? 
                null : 
                digitalProcessMapper
                .toDTO(
                    ctx.getSource()
                );
        TypeMap<PdiDataHistory, PdiDTO> typeMap = getTypeMap(
            PdiDataHistory.class, 
            PdiDTO.class
        );
        typeMap.addMappings(
            mapper -> {
                mapper.using(converterDigitalProcess)
                    .map(
                        PdiDataHistory::getDigitalProcess,
                        PdiDTO::setDigitalProcess
                    );
                mapper.using(converterListValueParameter)
                    .map(
                        PdiDataHistory::getValueParameters, 
                        PdiDTO::setValueParameters
                    );
                mapper.map(
                    src -> src.getPipeline().getId(), 
                    PdiDTO::setPipelineId
                );
            }
        );
        PdiDTO dto = modelMapper.map(
            model, 
            PdiDTO.class
        );
        return dto;
    }

    @Override
    public PdiDataHistory fromDTO(PdiDTO dto) {
        return null;
    }
    
}
