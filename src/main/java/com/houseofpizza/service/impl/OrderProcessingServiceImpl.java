package com.houseofpizza.service.impl;

import com.houseofpizza.bin.OrderProcessingBin;
import com.houseofpizza.entity.PizzaToOrderEntity;
import com.houseofpizza.entity.StatusEntity;
import com.houseofpizza.error.ErrorCodes;
import com.houseofpizza.error.ErrorException;
import com.houseofpizza.jpa.PizzaToOrderRepository;
import com.houseofpizza.jpa.StatusRepository;
import com.houseofpizza.jpa.specification.PizzaToOrderSpecification;
import com.houseofpizza.jpa.specification.StatusSpecification;
import com.houseofpizza.service.OrderProcessingService;
import com.google.common.collect.Iterables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.houseofpizza.error.ErrorException.checkNotEmptyListOrThrowNotFound;

@Service
public class OrderProcessingServiceImpl implements OrderProcessingService {

    private Logger logger = LoggerFactory.getLogger(OrderProcessingServiceImpl.class);

    @Autowired
    private PizzaToOrderRepository pizzaToOrderRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public OrderProcessingBin getOrderProcessing() throws ErrorException, InterruptedException {
        logger.info("Begin service method getOrderProcessing");
        List<Integer> orderNumbers = new ArrayList<>();

        List<StatusEntity> statusList = retrieveStatusByStatus("In coda");
        for (StatusEntity element : statusList) {
            PizzaToOrderEntity pizzaToOrder = retrievePizzaToOrderByIdStatus(element.getId());

            element.setStatus("In lavorazione");
            logger.info("I process the order number: {} and pizzaId: {} and status: {}", pizzaToOrder.getIdOrder(), pizzaToOrder.getIdPizza(), element.getStatus());
            statusRepository.saveAndFlush(element);

            TimeUnit.SECONDS.sleep(10);

            element.setStatus("Completato");
            logger.info("Finished processing for order: {} and pizzaId: {} and status: {}", pizzaToOrder.getIdOrder(), pizzaToOrder.getIdPizza(), element.getStatus());
            statusRepository.saveAndFlush(element);
            orderNumbers.add(pizzaToOrder.getIdOrder());
        }

        logger.info("End service method getOrderProcessing");
        return OrderProcessingBin.builder()
                .orderNumber(orderNumbers)
                .build();
    }

    private List<StatusEntity> retrieveStatusByStatus(String status) {
        Specification<StatusEntity> statusSpecification = StatusSpecification.withStatusEqualTo(status);
        return checkNotEmptyListOrThrowNotFound(statusRepository.findAll(statusSpecification), ErrorCodes.ERROR04);
    }

    private PizzaToOrderEntity retrievePizzaToOrderByIdStatus(Integer idStatus) {
        Specification<PizzaToOrderEntity> pizzaToOrderSpecification = PizzaToOrderSpecification.withIdStatusEqualTo(idStatus);
        return Optional.of(pizzaToOrderRepository.findAll(pizzaToOrderSpecification))
                .map(list -> Iterables.getFirst(list, null))
                .get();
    }

}
