package com.houseofpizza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.houseofpizza.repository.PizzaRepository;
import com.houseofpizza.repository.PizzaToOrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderStatusService {
    // TODO : Delete this class

    @Autowired
    private PizzaToOrderRepository pizzaToOrderRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

//    private List<PizzaToOrder> retrievePizzaToOrderByOrderNumber(Long orderNumber) throws ErrorException {
//        Specification<PizzaToOrder> pizzaToOrderSpecification =
//            PizzaToOrderSpecificationBuilder.withIdOrderEqualTo(orderNumber);
//        return checkNotEmptyListOrThrowNotFound(pizzaToOrderRepository.findAll(pizzaToOrderSpecification),
//            ErrorCodes.ORDER_NOT_FOUND);
//    }

//    private Pizza retrievePizzaByIdPizza(Long idPizza) {
//        Specification<Pizza> pizzaSpecification = PizzaSpecificationBuilder.withIdPizzaEqualTo(idPizza);
//        return extractFirstOrThrowNotFound(pizzaRepository.findAll(pizzaSpecification), ErrorCodes.PIZZA_NOT_FOUND);
//    }

//    private Status retrieveStatusByStatusId(Long statusId) {
//        Specification<Status> pizzaSpecification = StatusSpecificationBuilder.withStatusIdEqualTo(statusId);
//        return extractFirstOrThrowNotFound(statusRepository.findAll(pizzaSpecification), ErrorCodes.STATUS_NOT_FOUND);
//    }

//    @Transactional
//    public Status saveBaseStatusOrder() {
//        return statusRepository.save(buildQueueOrder());
//    }

}
