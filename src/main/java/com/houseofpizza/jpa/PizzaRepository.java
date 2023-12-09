package com.houseofpizza.jpa;

import com.houseofpizza.entity.PizzaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public interface PizzaRepository extends JpaRepository<PizzaEntity, Integer>, JpaSpecificationExecutor<PizzaEntity> {

}