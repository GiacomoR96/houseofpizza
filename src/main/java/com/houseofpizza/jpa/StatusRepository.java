package com.houseofpizza.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.houseofpizza.entity.Status;

@Component
@Transactional(readOnly = true)
public interface StatusRepository extends JpaRepository<Status, Integer>, JpaSpecificationExecutor<Status> {

}
