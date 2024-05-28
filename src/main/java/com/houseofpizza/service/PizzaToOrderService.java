package com.houseofpizza.service;

import static com.houseofpizza.factory.PizzaToOrderFactory.buildBasePizzaToOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.houseofpizza.repository.PizzaToOrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PizzaToOrderService {

    @Autowired
    private PizzaToOrderRepository repository;

    @Transactional
    public void saveBasePizzaToOrder(Long idOrder, Long idPizza, Long idStatus) {
        repository.save(buildBasePizzaToOrder(idOrder, idPizza, idStatus));
    }

}
