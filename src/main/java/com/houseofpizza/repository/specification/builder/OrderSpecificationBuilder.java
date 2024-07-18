package com.houseofpizza.repository.specification.builder;

import org.springframework.data.jpa.domain.Specification;

import com.houseofpizza.model.Order;
import com.houseofpizza.repository.specification.OrderSpecification;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderSpecificationBuilder {

    public Specification<Order> withPersonNameEqualToAndOrderActive(String personName) {
        return Specification.where(OrderSpecification.withPersonNameEqualTo(personName))
            .and(OrderSpecification.withOrderActive());
    }

}

