package com.houseofpizza.jpa.specification;

import com.houseofpizza.entity.PizzaEntity;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class PizzaSpecification {

    public Specification<PizzaEntity> withPizzaIdEqualTo(final Integer pizzaId) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .equal(root.get(PizzaEntity.PIZZA_ID_FIELD), pizzaId));
    }

    public Specification<PizzaEntity> withNameEqualTo(final String name) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .equal(root.get(PizzaEntity.NAME_FIELD), name));
    }

}