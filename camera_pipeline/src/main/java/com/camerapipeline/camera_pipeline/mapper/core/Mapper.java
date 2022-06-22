package com.camerapipeline.camera_pipeline.mapper.core;

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
    public abstract List<D> toDTOList(List<M> modelList);
    public abstract List<M> toModelList(List<D> modelList);
    protected TypeMap<M, D> getTypeMap(Class<M> modelClass, Class<D> dtoClass) {
        TypeMap<M, D> typeMap 
            = modelMapper.getTypeMap(
                modelClass, 
                dtoClass
            );
        if (typeMap == null) {
            typeMap = modelMapper.createTypeMap(
                modelClass, 
                dtoClass
            );
        }
        return typeMap;
    }
}
