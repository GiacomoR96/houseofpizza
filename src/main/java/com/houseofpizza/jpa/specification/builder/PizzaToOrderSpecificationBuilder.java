package com.houseofpizza.jpa.specification.builder;

import com.houseofpizza.entity.PizzaToOrderEntity;
import com.houseofpizza.jpa.specification.PizzaToOrderSpecification;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class PizzaToOrderSpecificationBuilder {

    public Specification<PizzaToOrderEntity> withIdOrderEqualTo(Integer orderNumber) {
        return Specification.where(PizzaToOrderSpecification.withIdOrderEqualTo(orderNumber));
    }

}