package com.houseofpizza.jpa.specification.builder;

import org.springframework.data.jpa.domain.Specification;

import com.houseofpizza.entity.PizzaToOrder;
import com.houseofpizza.jpa.specification.PizzaToOrderSpecification;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PizzaToOrderSpecificationBuilder {

    public Specification<PizzaToOrder> withIdOrderEqualTo(Integer orderNumber) {
        return Specification.where(PizzaToOrderSpecification.withIdOrderEqualTo(orderNumber));
    }

}