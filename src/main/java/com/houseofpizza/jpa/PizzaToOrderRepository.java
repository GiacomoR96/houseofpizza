package com.houseofpizza.jpa;

import com.houseofpizza.entity.PizzaToOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public interface PizzaToOrderRepository extends JpaRepository<PizzaToOrderEntity, Integer>, JpaSpecificationExecutor<PizzaToOrderEntity> {

}
