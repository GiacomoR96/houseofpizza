package com.houseofpizza.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.houseofpizza.enums.StatusEnum;
import com.houseofpizza.model.PizzaToOrder;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PizzaToOrderSpecification {

    public Specification<PizzaToOrder> withStatusOrderIsQueue() {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
            .equal(root.get(PizzaToOrder.Fields.status), StatusEnum.QUEUE));
    }

}
