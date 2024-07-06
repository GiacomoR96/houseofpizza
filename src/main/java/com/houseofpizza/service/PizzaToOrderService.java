package com.houseofpizza.service;

import org.springframework.stereotype.Service;

import com.houseofpizza.model.PizzaToOrder;
import com.houseofpizza.repository.PizzaToOrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PizzaToOrderService extends BaseService<PizzaToOrderRepository, PizzaToOrder, Long> {

    protected PizzaToOrderService(PizzaToOrderRepository repository) {
        super(repository);
    }

}
