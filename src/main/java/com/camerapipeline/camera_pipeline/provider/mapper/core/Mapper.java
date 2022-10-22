package com.camerapipeline.camera_pipeline.provider.mapper.core;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

// M -> Model
// D -> DTO
public abstract class Mapper<M, DTO> {
    @Autowired
    protected ModelMapper modelMapper;

    public abstract DTO toDTO(M model);
    public abstract M fromDTO(DTO dto);
    public List<DTO> toDTOList(List<M> modelList) {
        return modelList.stream().map(this::toDTO).collect(Collectors.toList());
    }
    public List<M> fromDTOList(List<DTO> dtoList) {
        return dtoList.stream().map(this::fromDTO).collect(Collectors.toList());
    }
    protected <S,R> TypeMap<S, R> getTypeMap(Class<S> sourceClass, Class<R> resultClass) {
        TypeMap<S, R> typeMap 
            = modelMapper.getTypeMap(
                sourceClass, 
                resultClass
            );
        if (typeMap == null) {
            typeMap = modelMapper.createTypeMap(
                sourceClass, 
                resultClass
            );
        }
        return typeMap;
    }

    public Page<DTO> toDTOPage(Page<M> modelList) {
        return modelList.map(this::toDTO);
    }
}
