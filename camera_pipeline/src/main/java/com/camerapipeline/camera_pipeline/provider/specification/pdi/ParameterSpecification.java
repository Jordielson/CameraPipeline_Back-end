package com.camerapipeline.camera_pipeline.provider.specification.pdi;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.camerapipeline.camera_pipeline.model.entities.pdi.ModelPDI;
import com.camerapipeline.camera_pipeline.model.entities.pdi.ModelPDI_;
import com.camerapipeline.camera_pipeline.model.entities.pdi.Parameter;
import com.camerapipeline.camera_pipeline.model.entities.pdi.Parameter_;

public class ParameterSpecification implements Specification<Parameter> {

    private final Parameter criteria;

    public ParameterSpecification(Parameter criteria) {
        this.criteria=criteria;
    }

    @Override
    public Predicate toPredicate(Root<Parameter> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        final List<Predicate> predicates = new ArrayList<Predicate>();

        Join<Parameter, ModelPDI> modelPdi = root.join("modelPdi");

        if(criteria.getName()!=null) {
            predicates.add(cb.like(cb.lower(root.get(Parameter_.name)), "%" + criteria.getName().toLowerCase() + "%"));
        }
        if(criteria.getUser()!=null) {
            predicates.add(cb.equal(modelPdi.get(ModelPDI_.user), criteria.getUser()));
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
