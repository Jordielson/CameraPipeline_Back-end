package com.camerapipeline.camera_pipeline.provider.mapper.history;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.model.entities.history.PdiDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.history.ValueParameterDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.pdi.ModelPDI;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.modelpdi.ModelPdiDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.pdi.PdiDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.valueparameter.ValueParameterDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.provider.mapper.pdi.ModelPDIMapper;
import com.camerapipeline.camera_pipeline.provider.services.pipeline.PipelineService;

@Component
public class PdiDataHistoryMapper extends Mapper<PdiDataHistory, PdiDTO>{
    @Autowired
    ValueParameterDataHistoryMapper valueParameterMapper;
    @Autowired
    ModelPDIMapper modelPDIMapper;
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
        Converter<ModelPDI, ModelPdiDTO> converterModelPDI =
            ctx -> ctx.getSource() == null ? 
                null : 
                modelPDIMapper.toDTO(
                    ctx.getSource()
                );
        TypeMap<PdiDataHistory, PdiDTO> typeMap = getTypeMap(
            PdiDataHistory.class, 
            PdiDTO.class
        );
        typeMap.addMappings(
            mapper -> {
                mapper.using(converterModelPDI)
                    .map(
                        PdiDataHistory::getModelPdi,
                        PdiDTO::setModelPdi
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
