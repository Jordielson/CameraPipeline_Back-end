package com.camerapipeline.camera_pipeline.provider.services.mosaic;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.entities.mosaic.Mosaic;
import com.camerapipeline.camera_pipeline.model.repository.mosaic.MosaicRepository;
import com.camerapipeline.camera_pipeline.provider.exception.CustomEntityNotFoundException;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;
import com.camerapipeline.camera_pipeline.provider.specification.mosaic.MosaicSpecification;

@Service
public class MosaicService extends ServiceAbstract<Mosaic, Integer> {

    public MosaicService(MosaicRepository repository) {
        super(repository);
    }

    @Override
    protected Specification<Mosaic> getSpecification(Mosaic search) {
        return new MosaicSpecification(search);
    }

    @Override
    protected EntityNotFoundException throwNotFoundEntity(Integer id) {
        return new CustomEntityNotFoundException("Mosaic", id.toString());
    }
    
}
