package com.camerapipeline.camera_pipeline.provider.specification.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.camerapipeline.camera_pipeline.model.entities.user.User;

public class UserSpecification implements Specification<User> {

    private final User criteria;

    public UserSpecification(User criteria) {
        this.criteria=criteria;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        final List<Predicate> predicates = new ArrayList<Predicate>();
        if(criteria.getEmail()!=null) {
            predicates.add(cb.like(cb.lower(root.get("email")), "%" + criteria.getEmail().toLowerCase() + "%"));
        }
        if(criteria.getUser()!=null) {
            predicates.add(cb.equal(root.get("id"), criteria.getUser().getId()));
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
