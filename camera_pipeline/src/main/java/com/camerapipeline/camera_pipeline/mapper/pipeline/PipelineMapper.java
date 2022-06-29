package com.camerapipeline.camera_pipeline.mapper.pipeline;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.dto.camera.CameraDTO;
import com.camerapipeline.camera_pipeline.dto.pdi.PdiDTO;
import com.camerapipeline.camera_pipeline.dto.pipeline.PipelineDTO;
import com.camerapipeline.camera_pipeline.mapper.camera.CameraMapper;
import com.camerapipeline.camera_pipeline.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.mapper.pdi.PdiMapper;
import com.camerapipeline.camera_pipeline.model.camera.Camera;
import com.camerapipeline.camera_pipeline.model.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.pipeline.GroupPipeline;
import com.camerapipeline.camera_pipeline.model.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.services.pipeline.GroupPipelineService;

@Component
public class PipelineMapper extends Mapper<Pipeline, PipelineDTO>{
    @Autowired
    PdiMapper pdiMapper;
    @Autowired
    CameraMapper cameraMapper;
    @Autowired
    GroupPipelineService gPipelineService;

    @Override
    public PipelineDTO toDTO(Pipeline model) {
        TypeMap<Pipeline, PipelineDTO> typeMap = getTypeMap(
            Pipeline.class, 
            PipelineDTO.class
        );
        Converter<List<PDI>, List<PdiDTO>> converterPDIList =
            ctx -> ctx.getSource() == null ? null : pdiMapper.toDTOList(ctx.getSource());
        Converter<List<Camera>, List<CameraDTO>> converterCameraList =
            ctx -> ctx.getSource() == null ? null : cameraMapper.toDTOList(ctx.getSource());
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
                mapper.using(converterCameraList)
                    .map(
                        Pipeline::getCameraList, 
                        PipelineDTO::setCameraList
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
        TypeMap<PipelineDTO, Pipeline> typeMap = getTypeMap(
            PipelineDTO.class, 
            Pipeline.class
        );
        Converter<List<PdiDTO>, List<PDI>> converterPDIList =
            ctx -> ctx.getSource() == null ? null : pdiMapper.fromDTOList(ctx.getSource());
        Converter<Integer, GroupPipeline> converterGroupPipeline =
            ctx -> ctx.getSource() == null ? 
                null : 
                gPipelineService.getById(ctx.getSource());
        Converter<List<CameraDTO>, List<Camera>> converterCameraList =
            ctx -> ctx.getSource() == null ? null : cameraMapper.fromDTOList(ctx.getSource());
        typeMap.addMappings(
            mapper -> {
                mapper.using(converterGroupPipeline)
                    .map(
                        src -> src.getGroupPipelineId(), 
                        Pipeline::setGroupPipeline
                    );
                mapper.using(converterPDIList)
                    .map(
                        PipelineDTO::getPDIList,
                        Pipeline::setPDIList
                    );
                mapper.using(converterCameraList)
                    .map(
                        PipelineDTO::getCameraList,
                        Pipeline::setCameraList
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
