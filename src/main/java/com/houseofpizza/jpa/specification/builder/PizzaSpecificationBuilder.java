package com.houseofpizza.jpa.specification.builder;

import org.springframework.data.jpa.domain.Specification;

import com.houseofpizza.entity.Pizza;
import com.houseofpizza.jpa.specification.PizzaSpecification;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PizzaSpecificationBuilder {

    public Specification<Pizza> withPizzaIdEqualTo(Integer pizzaId) {
        return Specification.where(PizzaSpecification.withPizzaIdEqualTo(pizzaId));
    }

    public Specification<Pizza> withNameEqualTo(String name) {
        return Specification.where(PizzaSpecification.withNameEqualTo(name));
    }

}