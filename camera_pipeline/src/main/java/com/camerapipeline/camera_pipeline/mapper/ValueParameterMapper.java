package com.camerapipeline.camera_pipeline.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.dto.ParameterDTO;
import com.camerapipeline.camera_pipeline.dto.ValueParameterDTO;
import com.camerapipeline.camera_pipeline.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.model.Parameter;
import com.camerapipeline.camera_pipeline.model.ValueParameter;

@Component
public class ValueParameterMapper extends Mapper<ValueParameter, ValueParameterDTO> {
    @Autowired
    ParameterMapper parameterMapper;

    public TypeMap<ValueParameter, ValueParameterDTO> getTypeMap() {
        return super.getTypeMap(
            ValueParameter.class, 
            ValueParameterDTO.class
        );
    }

    @Override
    public ValueParameterDTO toDTO(ValueParameter model) {
        TypeMap<ValueParameter, ValueParameterDTO> typeMap = getTypeMap();
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
                        ValueParameter::getParameter,
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
    public ValueParameter fromDTO(ValueParameterDTO dto) {
        return null;
    }

    @Override
    public List<ValueParameterDTO> toDTOList(List<ValueParameter> modelList) {
        List<ValueParameterDTO> list = new ArrayList<ValueParameterDTO>();
        for (ValueParameter value : modelList) {
            list.add(toDTO(value));
        }
        return list;
    }

    @Override
    public List<ValueParameter> toModelList(List<ValueParameterDTO> modelList) {
        return null;
    }

}
