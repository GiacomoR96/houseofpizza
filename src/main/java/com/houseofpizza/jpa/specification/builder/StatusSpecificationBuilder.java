package com.houseofpizza.jpa.specification.builder;

import org.springframework.data.jpa.domain.Specification;

import com.houseofpizza.entity.Status;
import com.houseofpizza.jpa.specification.StatusSpecification;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StatusSpecificationBuilder {

    public Specification<Status> withStatusIdEqualTo(Integer statusId) {
        return Specification.where(StatusSpecification.withStatusIdEqualTo(statusId));
    }

}