package com.camerapipeline.camera_pipeline.provider.mapper.core;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

// M -> Model
// D -> DTO
public abstract class Mapper<M, REQUEST, RESPONSE> {
    @Autowired
    protected ModelMapper modelMapper;

    public abstract RESPONSE toDTO(M model);
    public abstract M fromDTO(REQUEST dto);
    public List<RESPONSE> toDTOList(List<M> modelList) {
        List<RESPONSE> list = new ArrayList<>();
        for (M model : modelList) {
            list.add(toDTO(model));
        }
        return list;
    }
    public List<M> fromDTOList(List<REQUEST> dtoList) {
        List<M> list = new ArrayList<>();
        for (REQUEST dto : dtoList) {
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

    public Page<RESPONSE> toDTOPage(Page<M> modelList) {
        return modelList.map(this::toDTO);
    }
}
