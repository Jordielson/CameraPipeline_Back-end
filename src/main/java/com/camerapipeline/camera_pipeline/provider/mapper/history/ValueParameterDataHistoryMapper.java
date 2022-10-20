package com.camerapipeline.camera_pipeline.provider.mapper.history;

import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.model.entities.history.ValueParameterDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.pdi.Parameter;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.parameter.ParameterDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.valueparameter.ValueParameterDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.provider.mapper.pdi.ParameterMapper;

@Component
public class ValueParameterDataHistoryMapper extends Mapper<ValueParameterDataHistory, ValueParameterDTO> {
    @Autowired
    ParameterMapper parameterMapper;

    @Override
    public ValueParameterDTO toDTO(ValueParameterDataHistory model) {
        TypeMap<ValueParameterDataHistory, ValueParameterDTO> typeMap = getTypeMap(
            ValueParameterDataHistory.class, 
            ValueParameterDTO.class
        );
        Converter<Parameter, ParameterDTO> converterListParameter =
            ctx -> ctx.getSource() == null ? 
                null : 
                parameterMapper.toDTO(
                    ctx.getSource()
                );
        typeMap.addMappings(
            mapper -> {
                mapper.using(converterListParameter)
                    .map(
                        ValueParameterDataHistory::getParameter,
                        ValueParameterDTO::setParameter
                    );
            }
        );

        ValueParameterDTO dto = modelMapper.map(
            model, 
            ValueParameterDTO.class
        );
        return dto;
    }

    @Override
    public ValueParameterDataHistory fromDTO(ValueParameterDTO dto) {
        TypeMap<ValueParameterDTO, ValueParameterDataHistory> typeMap = getTypeMap(
            ValueParameterDTO.class, 
            ValueParameterDataHistory.class
        );
        Converter<ParameterDTO, Parameter> converterListParameter =
            ctx -> ctx.getSource() == null ? 
                null : 
                parameterMapper.fromDTO(
                    ctx.getSource()
                );
        typeMap.addMappings(
            mapper -> {
                mapper.using(converterListParameter)
                    .map(
                        ValueParameterDTO::getParameter,
                        ValueParameterDataHistory::setParameter
                    );
            }
        );

        ValueParameterDataHistory value = modelMapper.map(
            dto, 
            ValueParameterDataHistory.class
        );
        return value;
    }
    
}
