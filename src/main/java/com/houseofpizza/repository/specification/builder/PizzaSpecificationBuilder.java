package com.houseofpizza.repository.specification.builder;

import org.springframework.data.jpa.domain.Specification;

import com.houseofpizza.model.Pizza;
import com.houseofpizza.repository.specification.PizzaSpecification;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PizzaSpecificationBuilder {

    public Specification<Pizza> withIdPizzaEqualTo(Long idPizza) {
        return Specification.where(PizzaSpecification.withPizzaIdEqualTo(idPizza));
    }

}
