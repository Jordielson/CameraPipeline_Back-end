package com.camerapipeline.camera_pipeline.provider.specification.pdi;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.camerapipeline.camera_pipeline.model.entities.pdi.ModelPDI;

public class ModelPDISpecification implements Specification<ModelPDI> {

    private final ModelPDI criteria;

    public ModelPDISpecification(ModelPDI criteria) {
        this.criteria=criteria;
    }

    @Override
    public Predicate toPredicate(Root<ModelPDI> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        final List<Predicate> predicates = new ArrayList<Predicate>();
        if(criteria.getName()!=null) {
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + criteria.getName().toLowerCase() + "%"));
        }
        if(criteria.getCategory()!=null) {
            predicates.add(cb.equal(root.get("category"), criteria.getCategory()));
        }
        if(criteria.getUser()!=null) {
            predicates.add(cb.equal(root.get("user"), criteria.getUser()));
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
