package com.camerapipeline.camera_pipeline.provider.specification.input.video;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.camerapipeline.camera_pipeline.model.entities.input.video.VideoData;

public class VideoSpecification implements Specification<VideoData> {

    private final VideoData criteria;

    public VideoSpecification(VideoData criteria) {
        this.criteria=criteria;
    }
    
    @Override
    public Predicate toPredicate(Root<VideoData> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        final List<Predicate> predicates = new ArrayList<Predicate>();
        if(criteria.getName()!=null) {
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + criteria.getName().toLowerCase() + "%"));
        }
        if(criteria.getFormat()!=null) {
            predicates.add(cb.equal(root.get("format"), criteria.getFormat()));
        }
        if(criteria.getUser()!=null) {
            predicates.add(cb.equal(root.get("user"), criteria.getUser()));
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}