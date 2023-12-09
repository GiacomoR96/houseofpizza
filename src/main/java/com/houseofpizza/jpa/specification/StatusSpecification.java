package com.houseofpizza.jpa.specification;

import com.houseofpizza.entity.StatusEntity;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import static com.houseofpizza.entity.StatusEntity.STATUS_FIELD;
import static com.houseofpizza.entity.StatusEntity.STATUS_ID_FIELD;

@UtilityClass
public class StatusSpecification {

    public Specification<StatusEntity> withStatusIdEqualTo(final Integer statusId) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .equal(root.get(STATUS_ID_FIELD), statusId));
    }

    public Specification<StatusEntity> withStatusEqualTo(final String status) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .equal(root.get(STATUS_FIELD), status));
    }

}
