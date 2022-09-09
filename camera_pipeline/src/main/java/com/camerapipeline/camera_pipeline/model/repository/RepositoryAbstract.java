package com.camerapipeline.camera_pipeline.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RepositoryAbstract<M, ID> extends JpaRepository<M, ID>{

    default Page<M> findAll(Pageable pageable, Integer userId) {
        return findAll(pageable);
    }
}
