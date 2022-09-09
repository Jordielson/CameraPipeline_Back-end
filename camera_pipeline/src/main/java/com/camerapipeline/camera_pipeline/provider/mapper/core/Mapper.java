package com.camerapipeline.camera_pipeline.provider.mapper.core;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;

// M -> Model
// D -> DTO
public abstract class Mapper<M, D> {
    @Autowired
    protected ModelMapper modelMapper;

    public abstract D toDTO(M model);
    public abstract M fromDTO(D dto);
    public List<D> toDTOList(List<M> modelList) {
        List<D> list = new ArrayList<>();
        for (M model : modelList) {
            list.add(toDTO(model));
        }
        return list;
    }
    public List<M> fromDTOList(List<D> dtoList) {
        List<M> list = new ArrayList<>();
        for (D dto : dtoList) {
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
}
