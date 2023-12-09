package com.houseofpizza.jpa.specification.builder;

import com.houseofpizza.entity.OrderEntity;
import com.houseofpizza.jpa.specification.OrderSpecification;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class OrderSpecificationBuilder {

    public Specification<OrderEntity> withPersonNameEqualTo(String personName) {
        return Specification.where(OrderSpecification.withPersonNameEqualTo(personName));
    }

    public Specification<OrderEntity> withEmailEqualTo(String email) {
        return Specification.where(OrderSpecification.withEmailEqualTo(email));
    }

    public Specification<OrderEntity> withPersonNameAndEmailEqualTo(String personName, String email) {
        return withPersonNameEqualTo(personName).and(withEmailEqualTo(email));
    }

}