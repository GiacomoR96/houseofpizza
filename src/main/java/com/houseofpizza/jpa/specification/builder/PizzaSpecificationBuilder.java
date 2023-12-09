package com.houseofpizza.jpa.specification.builder;

import com.houseofpizza.entity.PizzaEntity;
import com.houseofpizza.jpa.specification.PizzaSpecification;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class PizzaSpecificationBuilder {

    public Specification<PizzaEntity> withPizzaIdEqualTo(Integer pizzaId) {
        return Specification.where(PizzaSpecification.withPizzaIdEqualTo(pizzaId));
    }

    public Specification<PizzaEntity> withNameEqualTo(String name) {
        return Specification.where(PizzaSpecification.withNameEqualTo(name));
    }

}