package com.houseofpizza.service.impl;

import static com.houseofpizza.error.ErrorException.checkNotEmptyListOrThrowNotFound;
import static com.houseofpizza.error.ErrorException.extractFirstOrThrowNotFound;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.houseofpizza.bin.StatusOrderBin;
import com.houseofpizza.entity.Pizza;
import com.houseofpizza.entity.PizzaToOrder;
import com.houseofpizza.entity.Status;
import com.houseofpizza.error.ErrorCodes;
import com.houseofpizza.error.ErrorException;
import com.houseofpizza.jpa.PizzaRepository;
import com.houseofpizza.jpa.PizzaToOrderRepository;
import com.houseofpizza.jpa.StatusRepository;
import com.houseofpizza.jpa.specification.builder.PizzaSpecificationBuilder;
import com.houseofpizza.jpa.specification.builder.PizzaToOrderSpecificationBuilder;
import com.houseofpizza.jpa.specification.builder.StatusSpecificationBuilder;
import com.houseofpizza.service.StatusOrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class StatusOrderServiceImpl implements StatusOrderService {

    @Autowired
    private PizzaToOrderRepository pizzaToOrderRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public StatusOrderBin getStatusOrderService(StatusOrderBin statusOrderBin) throws ErrorException {
        log.info("Begin service method getStatusOrderService");
        Integer orderNumber = statusOrderBin.getOrderNumber();

        List<PizzaToOrder> pizzaToOrderList = retrievePizzaToOrderByOrderNumber(orderNumber);
        Map<Pizza, String> pizzaList = retrievePizzaList(pizzaToOrderList);

        log.info("Service list output : {}", pizzaList);
        log.info("End service method getStatusOrderService");
        return StatusOrderBin.builder()
            .orderNumber(orderNumber)
            .pizzaMap(pizzaList)
            .build();
    }

    private List<PizzaToOrder> retrievePizzaToOrderByOrderNumber(Integer orderNumber) throws ErrorException {
        Specification<PizzaToOrder> pizzaToOrderSpecification =
            PizzaToOrderSpecificationBuilder.withIdOrderEqualTo(orderNumber);
        return checkNotEmptyListOrThrowNotFound(pizzaToOrderRepository.findAll(pizzaToOrderSpecification),
            ErrorCodes.ORDER_NOT_FOUND);
    }

    private Pizza retrievePizzaByPizzaId(Integer pizzaId) {
        Specification<Pizza> pizzaSpecification = PizzaSpecificationBuilder.withPizzaIdEqualTo(pizzaId);
        return extractFirstOrThrowNotFound(pizzaRepository.findAll(pizzaSpecification), ErrorCodes.PIZZA_NOT_FOUND);
    }

    private Status retrieveStatusByStatusId(Integer statusId) {
        Specification<Status> pizzaSpecification = StatusSpecificationBuilder.withStatusIdEqualTo(statusId);
        return extractFirstOrThrowNotFound(statusRepository.findAll(pizzaSpecification), ErrorCodes.STATUS_NOT_FOUND);
    }

    private Map<Pizza, String> retrievePizzaList(List<PizzaToOrder> pizzaToOrderList) {
        Map<Pizza, String> map = new HashMap<>();

        for (PizzaToOrder pizzaOrder : pizzaToOrderList) {
            Pizza pizza = retrievePizzaByPizzaId(pizzaOrder.getIdPizza());
            Status status = retrieveStatusByStatusId(pizzaOrder.getIdStatus());
            map.put(pizza, status.getStatus());
        }
        return map;
    }

}
