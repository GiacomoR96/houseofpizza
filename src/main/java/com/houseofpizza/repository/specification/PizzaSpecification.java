package com.houseofpizza.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.houseofpizza.model.Pizza;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PizzaSpecification {

    public Specification<Pizza> withPizzaIdEqualTo(final Integer pizzaId) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
            .equal(root.get(Pizza.Fields.id), pizzaId));
    }

    public Specification<Pizza> withNameEqualTo(final String name) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
            .equal(root.get(Pizza.Fields.name), name));
    }

}