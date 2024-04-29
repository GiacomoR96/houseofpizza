package com.houseofpizza.repository.specification.builder;

import org.springframework.data.jpa.domain.Specification;

import com.houseofpizza.model.PizzaToOrder;
import com.houseofpizza.repository.specification.PizzaToOrderSpecification;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PizzaToOrderSpecificationBuilder {

    public Specification<PizzaToOrder> withIdOrderEqualTo(Integer orderNumber) {
        return Specification.where(PizzaToOrderSpecification.withIdOrderEqualTo(orderNumber));
    }

}