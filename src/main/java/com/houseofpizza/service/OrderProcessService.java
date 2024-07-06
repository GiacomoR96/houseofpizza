package com.houseofpizza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.houseofpizza.repository.PizzaToOrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderProcessService {

    @Autowired
    private PizzaToOrderRepository pizzaToOrderRepository;

//    public List<Long> getOrderProcessing() throws ErrorException, InterruptedException {
//        log.info("Begin service method getOrderProcessing");
//        List<Long> orderNumbers = new ArrayList<>();
//
//        List<Status> statusList = retrieveStatusByStatus("In queue");
//        for (Status element : statusList) {
//            PizzaToOrder pizzaToOrder = retrievePizzaToOrderByIdStatus(element.getId());
//
//            element.setStatus(StatusEnum.PROCESSING);
//            log.info("I process the order number: {} and pizzaId: {} and status: {}", pizzaToOrder.getIdOrder(),
//                pizzaToOrder.getIdPizza(), element.getStatus());
//            statusRepository.saveAndFlush(element);
//
//            TimeUnit.SECONDS.sleep(10);
//
//            element.setStatus(StatusEnum.COMPLETED);
//            log.info("Finished processing for order: {} and pizzaId: {} and status: {}", pizzaToOrder.getIdOrder(),
//                pizzaToOrder.getIdPizza(), element.getStatus());
//            statusRepository.saveAndFlush(element);
//            orderNumbers.add(pizzaToOrder.getIdOrder());
//        }
//
//        log.info("End service method getOrderProcessing");
//        return orderNumbers;
//    }

//    private List<Status> retrieveStatusByStatus(String status) {
//        Specification<Status> statusSpecification = StatusSpecification.withStatusEqualTo(status);
//        return checkNotEmptyListOrThrowNotFound(statusRepository.findAll(statusSpecification),
//            ErrorCodes.ELEMENTS_TO_ELABORATE_NOT_FOUND);
//    }

//    private PizzaToOrder retrievePizzaToOrderByIdStatus(Long idStatus) {
//        Specification<PizzaToOrder> pizzaToOrderSpecification =
//            PizzaToOrderSpecification.withIdStatusEqualTo(idStatus);
//        return extractFirstOrThrowNotFound(pizzaToOrderRepository.findAll(pizzaToOrderSpecification),
//            ErrorCodes.ORDER_NOT_FOUND);
//    }

}
