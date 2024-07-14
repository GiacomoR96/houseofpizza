package com.houseofpizza.service;

import static com.houseofpizza.exceptions.ErrorException.checkNotEmptyListOrThrowNotFound;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.houseofpizza.enums.LifecycleEnum;
import com.houseofpizza.enums.StatusEnum;
import com.houseofpizza.exceptions.ErrorCodes;
import com.houseofpizza.model.PizzaToOrder;
import com.houseofpizza.repository.PizzaToOrderRepository;
import com.houseofpizza.repository.specification.builder.PizzaToOrderSpecificationBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PizzaToOrderService extends BaseService<PizzaToOrderRepository, PizzaToOrder, Long> {

    protected PizzaToOrderService(PizzaToOrderRepository repository) {
        super(repository);
    }

//    public List<PizzaToOrder> retrievePizzaToOrderInQueueAndOrderActive() {
//        return repository.findByStatusEqualsAndOrderLifecycleEquals(StatusEnum.QUEUE.name(), LifecycleEnum.ACTIVE.name());
//    }

    public List<Long> processOrder() throws InterruptedException {
        log.info("Begin service method getOrderProcessing");
        List<Long> elaborateOrderIds = new ArrayList<>();
        List<PizzaToOrder> pizzaToOrders = retrieveOrderToProcess();

        for (PizzaToOrder element : pizzaToOrders) {
            element.setStatus(StatusEnum.PROCESSING);
            log.info("I process the order id: {} and pizza name: {} with status: {}", element.getOrder().getId(),
                element.getPizza().getName(), element.getStatus());
            repository.saveAndFlush(element);

            TimeUnit.SECONDS.sleep(3);

            element.setStatus(StatusEnum.COMPLETED);
            log.info("Finished processing for order id: {} and pizza name: {} with status: {}",
                element.getOrder().getId(),
                element.getPizza().getName(), element.getStatus());
            repository.saveAndFlush(element);
            elaborateOrderIds.add(element.getOrder().getId());
        }

        log.info("End service method getOrderProcessing");
        return elaborateOrderIds.stream()
            .distinct()
            .toList();
    }

    public List<PizzaToOrder> retrieveOrderToProcess() {
        Specification<PizzaToOrder> pizzaToOrderSpecification =
            PizzaToOrderSpecificationBuilder.withStatusOrderIsQueue();

        List<PizzaToOrder> result = checkNotEmptyListOrThrowNotFound(repository.findAll(pizzaToOrderSpecification),
            ErrorCodes.ELEMENTS_TO_ELABORATE_NOT_FOUND);

        return result.stream()
            .filter(e -> e.getOrder() != null && e.getOrder().getLifecycle() == LifecycleEnum.ACTIVE)
            .toList();
    }

}
