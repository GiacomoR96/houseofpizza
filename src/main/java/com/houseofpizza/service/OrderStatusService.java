package com.houseofpizza.service;

import static com.houseofpizza.exceptions.ErrorException.extractFirstOrThrowNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.houseofpizza.exceptions.ErrorCodes;
import com.houseofpizza.model.Pizza;
import com.houseofpizza.repository.PizzaRepository;
import com.houseofpizza.repository.PizzaToOrderRepository;
import com.houseofpizza.repository.specification.builder.PizzaSpecificationBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderStatusService {

    @Autowired
    private PizzaToOrderRepository pizzaToOrderRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

//    public Map<Pizza, StatusEnum> getStatusOrderService(Long order) throws ErrorException {
//        log.info("Begin service method getStatusOrderService");
//
//        List<PizzaToOrder> pizzaToOrderList = retrievePizzaToOrderByOrderNumber(order);
//        Map<Pizza, StatusEnum> pizzaList = retrievePizzaList(pizzaToOrderList);
//
//        log.info("Service list output : {}", pizzaList);
//        log.info("End service method getStatusOrderService");
//        return pizzaList;
//    }

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

    // TODO: THERE IS A PROBLEM HERE
//    private Map<Pizza, StatusEnum> retrievePizzaList(List<PizzaToOrder> pizzaToOrderList) {
//        Map<Pizza, StatusEnum> map = new HashMap<>();
//
//        for (PizzaToOrder pizzaOrder : pizzaToOrderList) {
//            Pizza pizza = retrievePizzaByIdPizza(pizzaOrder.getIdPizza());
//            Status status = retrieveStatusByStatusId(pizzaOrder.getIdStatus());
//            map.put(pizza, status.getStatus());
//        }
//        return map;
//    }

//    @Transactional
//    public Status saveBaseStatusOrder() {
//        return statusRepository.save(buildQueueOrder());
//    }

}
