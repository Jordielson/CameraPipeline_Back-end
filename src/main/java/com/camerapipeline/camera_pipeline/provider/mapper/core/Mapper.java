package com.camerapipeline.camera_pipeline.provider.mapper.core;

import java.util.ArrayList;
import java.util.List;

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
        List<DTO> list = new ArrayList<>();
        for (M model : modelList) {
            list.add(toDTO(model));
        }
        return list;
    }
    public List<M> fromDTOList(List<DTO> dtoList) {
        List<M> list = new ArrayList<>();
        for (DTO dto : dtoList) {
            list.add(fromDTO(dto));
        }
        return list;
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
