package com.camerapipeline.camera_pipeline.mapper.pdi;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.dto.PdiDTO;
import com.camerapipeline.camera_pipeline.dto.ValueParameterDTO;
import com.camerapipeline.camera_pipeline.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.model.ModelPDI;
import com.camerapipeline.camera_pipeline.model.PDI;
import com.camerapipeline.camera_pipeline.model.Pipeline;
import com.camerapipeline.camera_pipeline.model.ValueParameter;
import com.camerapipeline.camera_pipeline.services.pdi.ModelPDIService;
import com.camerapipeline.camera_pipeline.services.pipeline.PipelineService;

@Component
public class PdiMapper extends Mapper<PDI, PdiDTO>{
    @Autowired
    ValueParameterMapper valueParameterMapper;
    @Autowired
    ModelPDIService modelService;
    @Autowired
    PipelineService pipelineService;

    @Override
    public PdiDTO toDTO(PDI model) {
        Converter<List<ValueParameter>, List<ValueParameterDTO>> converterListValueParameter =
            ctx -> ctx.getSource() == null ? 
                null : 
                valueParameterMapper.toDTOList(
                    ctx.getSource()
                );
        TypeMap<PDI, PdiDTO> typeMap = getTypeMap(
            PDI.class, 
            PdiDTO.class
        );
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
    public PDI fromDTO(PdiDTO dto) {
        Converter<List<ValueParameterDTO>, List<ValueParameter>> converterListValueParameter =
            ctx -> ctx.getSource() == null ? 
                null : 
                valueParameterMapper.fromDTOList(
                    ctx.getSource()
                );
        TypeMap<PdiDTO, PDI> typeMap = getTypeMap(
            PdiDTO.class, 
            PDI.class
        );
        Converter<String, ModelPDI> converterModel =
            ctx -> ctx.getSource() == null ? 
                null : 
                modelService.getModelPDIByName(
                    ctx.getSource()
                );
        Converter<Integer, Pipeline> converterPipeline =
            ctx -> ctx.getSource() == null ? 
                null : 
                pipelineService.getPipeline(
                    ctx.getSource()
                );
        typeMap.addMappings(
            mapper -> {
                mapper.using(converterModel)
                    .map(
                        src -> src.getName(), 
                        PDI::setModelPDI
                    );
                mapper.using(converterListValueParameter)
                    .map(
                        PdiDTO::getValueParameters, 
                        PDI::setValueParameters
                    );
                mapper.using(converterPipeline)
                    .map(
                        PdiDTO::getPipelineId, 
                        PDI::setPipeline
                    );
            }
        );
        PDI pdi = modelMapper.map(
            dto, 
            PDI.class
        );
        return pdi;
    }
}
