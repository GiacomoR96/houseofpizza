package com.houseofpizza.jpa.specification;

import com.houseofpizza.entity.PizzaToOrderEntity;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import static com.houseofpizza.entity.PizzaToOrderEntity.ID_STATUS_FIELD;
import static com.houseofpizza.entity.PizzaToOrderEntity.NUMBER_ORDER_FIELD;

@UtilityClass
public class PizzaToOrderSpecification {

    public Specification<PizzaToOrderEntity> withIdOrderEqualTo(final Integer orderNumber) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .equal(root.get(NUMBER_ORDER_FIELD), orderNumber));
    }

    public Specification<PizzaToOrderEntity> withIdStatusEqualTo(final Integer idStatus) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .equal(root.get(ID_STATUS_FIELD), idStatus));
    }

}