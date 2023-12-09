package com.houseofpizza.jpa.specification.builder;

import com.houseofpizza.entity.StatusEntity;
import com.houseofpizza.jpa.specification.StatusSpecification;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class StatusSpecificationBuilder {

    public Specification<StatusEntity> withStatusIdEqualTo(Integer statusId) {
        return Specification.where(StatusSpecification.withStatusIdEqualTo(statusId));
    }

}