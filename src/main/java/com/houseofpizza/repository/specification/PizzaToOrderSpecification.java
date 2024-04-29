package com.houseofpizza.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.houseofpizza.model.PizzaToOrder;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PizzaToOrderSpecification {

    public Specification<PizzaToOrder> withIdOrderEqualTo(final Integer orderNumber) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
            .equal(root.get(PizzaToOrder.Fields.idOrder), orderNumber));
    }

    public Specification<PizzaToOrder> withIdStatusEqualTo(final Integer idStatus) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
            .equal(root.get(PizzaToOrder.Fields.idStatus), idStatus));
    }

}