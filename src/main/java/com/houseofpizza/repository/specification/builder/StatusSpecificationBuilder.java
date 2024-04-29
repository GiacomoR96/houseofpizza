package com.houseofpizza.repository.specification.builder;

import org.springframework.data.jpa.domain.Specification;

import com.houseofpizza.model.Status;
import com.houseofpizza.repository.specification.StatusSpecification;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StatusSpecificationBuilder {

    public Specification<Status> withStatusIdEqualTo(Integer statusId) {
        return Specification.where(StatusSpecification.withStatusIdEqualTo(statusId));
    }

}