package com.cyansnbrst.pr15.specifications;

import com.cyansnbrst.pr15.entities.GameAuthor;

import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.Attribute;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class GameAuthorSpecification implements Specification<GameAuthor> {

    private final String criteria;

    @Override
    public Predicate toPredicate(Root<GameAuthor> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        for (Attribute<? super GameAuthor, ?> attribute : root.getModel().getAttributes()) {
            if (attribute.getJavaType().equals(String.class)) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(attribute.getName())), "%" + criteria.toLowerCase() + "%"));
            }
        }
        return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }
}
