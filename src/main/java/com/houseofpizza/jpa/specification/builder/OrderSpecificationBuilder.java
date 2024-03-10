package com.houseofpizza.jpa.specification.builder;

import org.springframework.data.jpa.domain.Specification;

import com.houseofpizza.entity.Order;
import com.houseofpizza.jpa.specification.OrderSpecification;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderSpecificationBuilder {

    public Specification<Order> withPersonNameEqualTo(String personName) {
        return Specification.where(OrderSpecification.withPersonNameEqualTo(personName));
    }

    public Specification<Order> withEmailEqualTo(String email) {
        return Specification.where(OrderSpecification.withEmailEqualTo(email));
    }

    public Specification<Order> withPersonNameAndEmailEqualTo(String personName, String email) {
        return withPersonNameEqualTo(personName).and(withEmailEqualTo(email));
    }

}