package com.camerapipeline.camera_pipeline.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.dto.PdiDTO;
import com.camerapipeline.camera_pipeline.dto.ValueParameterDTO;
import com.camerapipeline.camera_pipeline.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.model.PDI;
import com.camerapipeline.camera_pipeline.model.ValueParameter;

@Component
public class PdiMapper extends Mapper<PDI, PdiDTO>{
    @Autowired
    ValueParameterMapper valueParameterMapper;

    public TypeMap<PDI, PdiDTO> getTypeMap() {
        return super.getTypeMap(
            PDI.class, 
            PdiDTO.class
        );
    }

    @Override
    public PdiDTO toDTO(PDI model) {
        Converter<List<ValueParameter>, List<ValueParameterDTO>> converterListValueParameter =
            ctx -> ctx.getSource() == null ? 
                null : 
                valueParameterMapper.toDTOList(
                    ctx.getSource()
                );
        TypeMap<PDI, PdiDTO> typeMap = getTypeMap();
        typeMap.addMappings(
            mapper -> {
                mapper.map(
                    src -> src.getModelPDI().getName(), 
                    PdiDTO::setName
                );
                mapper.using(converterListValueParameter)
                    .map(
                        PDI::getValueParameters, 
                        PdiDTO::setValueParameters
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
    public PDI fromDTO(PdiDTO dto) {
        return null;
    }
    
    @Override
    public List<PdiDTO> toDTOList(List<PDI> pdiList) {
        List<PdiDTO> list = new ArrayList<PdiDTO>();
        for (PDI pdi : pdiList) {
            list.add(toDTO(pdi));
        }
        return list;
    }
    
    @Override
    public List<PDI> toModelList(List<PdiDTO> modelList) {
        return null;
    }
}
