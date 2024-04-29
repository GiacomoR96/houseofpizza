package com.houseofpizza.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.houseofpizza.model.Order;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderSpecification {

    public Specification<Order> withPersonNameEqualTo(final String personName) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
            .equal(root.get(Order.Fields.personName), personName));
    }

    public Specification<Order> withEmailEqualTo(final String email) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
            .equal(root.get(Order.Fields.email), email));
    }

}