package com.houseofpizza.service.impl;

import static com.houseofpizza.error.ErrorException.checkNotEmptyListOrThrowNotFound;
import static com.houseofpizza.error.ErrorException.extractFirstOrThrowNotFound;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.houseofpizza.bin.OrderProcessingBin;
import com.houseofpizza.entity.PizzaToOrder;
import com.houseofpizza.entity.Status;
import com.houseofpizza.error.ErrorCodes;
import com.houseofpizza.error.ErrorException;
import com.houseofpizza.jpa.PizzaToOrderRepository;
import com.houseofpizza.jpa.StatusRepository;
import com.houseofpizza.jpa.specification.PizzaToOrderSpecification;
import com.houseofpizza.jpa.specification.StatusSpecification;
import com.houseofpizza.service.OrderProcessingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderProcessingServiceImpl implements OrderProcessingService {

    @Autowired
    private PizzaToOrderRepository pizzaToOrderRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public OrderProcessingBin getOrderProcessing() throws ErrorException, InterruptedException {
        log.info("Begin service method getOrderProcessing");
        List<Integer> orderNumbers = new ArrayList<>();

        List<Status> statusList = retrieveStatusByStatus("In coda");
        for (Status element : statusList) {
            PizzaToOrder pizzaToOrder = retrievePizzaToOrderByIdStatus(element.getId());

            element.setStatus("In lavorazione");
            log.info("I process the order number: {} and pizzaId: {} and status: {}", pizzaToOrder.getIdOrder(),
                pizzaToOrder.getIdPizza(), element.getStatus());
            statusRepository.saveAndFlush(element);

            TimeUnit.SECONDS.sleep(10);

            element.setStatus("Completato");
            log.info("Finished processing for order: {} and pizzaId: {} and status: {}", pizzaToOrder.getIdOrder(),
                pizzaToOrder.getIdPizza(), element.getStatus());
            statusRepository.saveAndFlush(element);
            orderNumbers.add(pizzaToOrder.getIdOrder());
        }

        log.info("End service method getOrderProcessing");
        return OrderProcessingBin.builder()
            .orderNumber(orderNumbers)
            .build();
    }

    private List<Status> retrieveStatusByStatus(String status) {
        Specification<Status> statusSpecification = StatusSpecification.withStatusEqualTo(status);
        return checkNotEmptyListOrThrowNotFound(statusRepository.findAll(statusSpecification), ErrorCodes.ERROR04);
    }

    private PizzaToOrder retrievePizzaToOrderByIdStatus(Integer idStatus) {
        Specification<PizzaToOrder> pizzaToOrderSpecification =
            PizzaToOrderSpecification.withIdStatusEqualTo(idStatus);
        return extractFirstOrThrowNotFound(pizzaToOrderRepository.findAll(pizzaToOrderSpecification),
            ErrorCodes.ERROR03);
    }

}
