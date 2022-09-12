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
import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;

public class PDISpecification implements Specification<PDI> {

    private final PDI criteria;

    public PDISpecification(PDI criteria) {
        this.criteria=criteria;
    }

    @Override
    public Predicate toPredicate(Root<PDI> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        final List<Predicate> predicates = new ArrayList<Predicate>();

        Join<PDI, ModelPDI> modelPdi = root.join("modelPdi");

        if(criteria.getUser()!=null) {
            predicates.add(cb.equal(modelPdi.get(ModelPDI_.user), criteria.getUser()));
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
