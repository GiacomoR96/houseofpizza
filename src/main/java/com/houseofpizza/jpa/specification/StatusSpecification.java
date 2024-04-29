package com.houseofpizza.jpa.specification;

import org.springframework.data.jpa.domain.Specification;

import com.houseofpizza.entity.Status;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StatusSpecification {

    public Specification<Status> withStatusIdEqualTo(final Integer statusId) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
            .equal(root.get(Status.Fields.id), statusId));
    }

    public Specification<Status> withStatusEqualTo(final String status) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
            .equal(root.get(Status.Fields.status), status));
    }

}
