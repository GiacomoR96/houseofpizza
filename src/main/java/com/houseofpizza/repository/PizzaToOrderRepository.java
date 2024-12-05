package com.houseofpizza.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.houseofpizza.model.PizzaToOrder;

@Component
@Transactional(readOnly = true)
public interface PizzaToOrderRepository extends BaseRepository<PizzaToOrder, Long> {
}
