package com.houseofpizza.jpa.specification;

import org.springframework.data.jpa.domain.Specification;

import com.houseofpizza.entity.Order;

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