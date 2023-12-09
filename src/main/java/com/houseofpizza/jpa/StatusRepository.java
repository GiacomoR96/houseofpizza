package com.houseofpizza.jpa;

import com.houseofpizza.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public interface StatusRepository extends JpaRepository<StatusEntity, Integer>, JpaSpecificationExecutor<StatusEntity> {

}
