package com.houseofpizza.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.houseofpizza.model.Order;

@Component
@Transactional(readOnly = true)
public interface OrderRepository extends BaseRepository<Order, Long> {

}
