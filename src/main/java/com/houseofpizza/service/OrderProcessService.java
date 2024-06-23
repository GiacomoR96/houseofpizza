package com.houseofpizza.service;

import static com.houseofpizza.exceptions.ErrorException.checkNotEmptyListOrThrowNotFound;
import static com.houseofpizza.exceptions.ErrorException.extractFirstOrThrowNotFound;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.houseofpizza.enums.StatusEnum;
import com.houseofpizza.exceptions.ErrorCodes;
import com.houseofpizza.exceptions.ErrorException;
import com.houseofpizza.model.PizzaToOrder;
import com.houseofpizza.model.Status;
import com.houseofpizza.repository.PizzaToOrderRepository;
import com.houseofpizza.repository.StatusRepository;
import com.houseofpizza.repository.specification.PizzaToOrderSpecification;
import com.houseofpizza.repository.specification.StatusSpecification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderProcessService {

    @Autowired
    private PizzaToOrderRepository pizzaToOrderRepository;

    @Autowired
    private StatusRepository statusRepository;

    public List<Long> getOrderProcessing() throws ErrorException, InterruptedException {
        log.info("Begin service method getOrderProcessing");
        List<Long> orderNumbers = new ArrayList<>();

        List<Status> statusList = retrieveStatusByStatus("In queue");
        for (Status element : statusList) {
            PizzaToOrder pizzaToOrder = retrievePizzaToOrderByIdStatus(element.getId());

            element.setStatus(StatusEnum.PROCESSING);
            log.info("I process the order number: {} and pizzaId: {} and status: {}", pizzaToOrder.getIdOrder(),
                pizzaToOrder.getIdPizza(), element.getStatus());
            statusRepository.saveAndFlush(element);

            TimeUnit.SECONDS.sleep(10);

            element.setStatus(StatusEnum.COMPLETED);
            log.info("Finished processing for order: {} and pizzaId: {} and status: {}", pizzaToOrder.getIdOrder(),
                pizzaToOrder.getIdPizza(), element.getStatus());
            statusRepository.saveAndFlush(element);
            orderNumbers.add(pizzaToOrder.getIdOrder());
        }

        log.info("End service method getOrderProcessing");
        return orderNumbers;
    }

    private List<Status> retrieveStatusByStatus(String status) {
        Specification<Status> statusSpecification = StatusSpecification.withStatusEqualTo(status);
        return checkNotEmptyListOrThrowNotFound(statusRepository.findAll(statusSpecification),
            ErrorCodes.ELEMENTS_TO_ELABORATE_NOT_FOUND);
    }

    private PizzaToOrder retrievePizzaToOrderByIdStatus(Long idStatus) {
        Specification<PizzaToOrder> pizzaToOrderSpecification =
            PizzaToOrderSpecification.withIdStatusEqualTo(idStatus);
        return extractFirstOrThrowNotFound(pizzaToOrderRepository.findAll(pizzaToOrderSpecification),
            ErrorCodes.ORDER_NOT_FOUND);
    }

}
