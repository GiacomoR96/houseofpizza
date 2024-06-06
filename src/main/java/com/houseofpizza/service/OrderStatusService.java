package com.houseofpizza.service;

import static com.houseofpizza.exceptions.ErrorException.checkNotEmptyListOrThrowNotFound;
import static com.houseofpizza.exceptions.ErrorException.extractFirstOrThrowNotFound;
import static com.houseofpizza.factory.StatusFactory.buildQueueOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.houseofpizza.exceptions.ErrorCodes;
import com.houseofpizza.exceptions.ErrorException;
import com.houseofpizza.model.Pizza;
import com.houseofpizza.model.PizzaToOrder;
import com.houseofpizza.model.Status;
import com.houseofpizza.repository.PizzaRepository;
import com.houseofpizza.repository.PizzaToOrderRepository;
import com.houseofpizza.repository.StatusRepository;
import com.houseofpizza.repository.specification.builder.PizzaSpecificationBuilder;
import com.houseofpizza.repository.specification.builder.PizzaToOrderSpecificationBuilder;
import com.houseofpizza.repository.specification.builder.StatusSpecificationBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderStatusService {

    @Autowired
    private PizzaToOrderRepository pizzaToOrderRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private StatusRepository statusRepository;

    public Map<Pizza, String> getStatusOrderService(Long orderNumber) throws ErrorException {
        log.info("Begin service method getStatusOrderService");

        List<PizzaToOrder> pizzaToOrderList = retrievePizzaToOrderByOrderNumber(orderNumber);
        Map<Pizza, String> pizzaList = retrievePizzaList(pizzaToOrderList);

        log.info("Service list output : {}", pizzaList);
        log.info("End service method getStatusOrderService");
        return pizzaList;
    }

    private List<PizzaToOrder> retrievePizzaToOrderByOrderNumber(Long orderNumber) throws ErrorException {
        Specification<PizzaToOrder> pizzaToOrderSpecification =
            PizzaToOrderSpecificationBuilder.withIdOrderEqualTo(orderNumber);
        return checkNotEmptyListOrThrowNotFound(pizzaToOrderRepository.findAll(pizzaToOrderSpecification),
            ErrorCodes.ORDER_NOT_FOUND);
    }

    private Pizza retrievePizzaByIdPizza(Long idPizza) {
        Specification<Pizza> pizzaSpecification = PizzaSpecificationBuilder.withIdPizzaEqualTo(idPizza);
        return extractFirstOrThrowNotFound(pizzaRepository.findAll(pizzaSpecification), ErrorCodes.PIZZA_NOT_FOUND);
    }

    private Status retrieveStatusByStatusId(Long statusId) {
        Specification<Status> pizzaSpecification = StatusSpecificationBuilder.withStatusIdEqualTo(statusId);
        return extractFirstOrThrowNotFound(statusRepository.findAll(pizzaSpecification), ErrorCodes.STATUS_NOT_FOUND);
    }

    // TODO: THERE IS A PROBLEM HERE
    private Map<Pizza, String> retrievePizzaList(List<PizzaToOrder> pizzaToOrderList) {
        Map<Pizza, String> map = new HashMap<>();

        for (PizzaToOrder pizzaOrder : pizzaToOrderList) {
            Pizza pizza = retrievePizzaByIdPizza(pizzaOrder.getIdPizza());
            Status status = retrieveStatusByStatusId(pizzaOrder.getIdStatus());
            map.put(pizza, status.getStatus());
        }
        return map;
    }

    @Transactional
    public Status saveBaseStatusOrder() {
        return statusRepository.save(buildQueueOrder());
    }

}
