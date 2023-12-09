package com.houseofpizza.jpa.specification;

import com.houseofpizza.entity.OrderEntity;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import static com.houseofpizza.entity.OrderEntity.EMAIL_FIELD;
import static com.houseofpizza.entity.OrderEntity.PERSON_NAME_FIELD;

@UtilityClass
public class OrderSpecification {

    public Specification<OrderEntity> withPersonNameEqualTo(final String personName) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .equal(root.get(PERSON_NAME_FIELD), personName));
    }

    public Specification<OrderEntity> withEmailEqualTo(final String email) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .equal(root.get(EMAIL_FIELD), email));
    }

}