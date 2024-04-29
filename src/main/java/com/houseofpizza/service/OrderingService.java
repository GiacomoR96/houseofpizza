package com.houseofpizza.service;

import static com.houseofpizza.exceptions.ErrorException.checkNotEmptyListOrThrowNotFound;
import static com.houseofpizza.exceptions.ErrorException.extractFirstOrThrowNotFound;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.houseofpizza.dto.OrderingBin;
import com.houseofpizza.exceptions.ErrorCodes;
import com.houseofpizza.exceptions.ErrorException;
import com.houseofpizza.model.Order;
import com.houseofpizza.model.Pizza;
import com.houseofpizza.model.PizzaToOrder;
import com.houseofpizza.model.Status;
import com.houseofpizza.repository.OrderRepository;
import com.houseofpizza.repository.PizzaRepository;
import com.houseofpizza.repository.PizzaToOrderRepository;
import com.houseofpizza.repository.StatusRepository;
import com.houseofpizza.repository.specification.builder.OrderSpecificationBuilder;
import com.houseofpizza.repository.specification.builder.PizzaSpecificationBuilder;
import com.houseofpizza.representation.dto.OrderingDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class OrderingService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PizzaToOrderRepository pizzaToOrderRepository;

    @Autowired
    private StatusRepository statusRepository;

    public OrderingBin postOrderingService(OrderingBin bin) throws ErrorException {
        log.info("Begin service method postOrderingService");
        List<OrderingDto> orderingDtoList = bin.getDto().getOrderingDtoList();

        checkNotEmptyListOrThrowNotFound(orderingDtoList, ErrorCodes.LIST_NOT_FOUND);
        Order order = retrieveOrderByPersonNameAndEmail(bin.getPersonName(), bin.getEmail());

        for (OrderingDto orderingDto : orderingDtoList) {
            Pizza pizza = retrievePizzaByName(orderingDto.getPizzaName());
            Status status = statusRepository.save(prepareStatusEntityForSave());
            pizzaToOrderRepository.save(preparePizzaToOrderEntityForSave(order.getId(), pizza.getId(), status.getId()));
        }

        log.info("Service orderId output : {}", order.getId());
        log.info("End service method postOrderingService");
        return OrderingBin.builder()
            .orderNumber(order.getId())
            .build();
    }

    private Order prepareOrderEntityForSave(String personName, LocalDate date, String email) {
        Order entity = new Order();
        entity.setPersonName(personName);
        entity.setDate(date);
        entity.setEmail(email);
        return entity;
    }

    private Order saveAndRetrieveOrderEntity(String personName, String email) {
        LocalDate date = LocalDate.now();
        return orderRepository.save(prepareOrderEntityForSave(personName, date, email));
    }

    private Order retrieveOrderByPersonNameAndEmail(String personName, String email) {
        Specification<Order> orderSpecification =
            OrderSpecificationBuilder.withPersonNameAndEmailEqualTo(personName, email);
        return Optional.of(orderRepository.findAll(orderSpecification)).flatMap(list -> list.stream().findFirst())
            .orElseGet(() -> saveAndRetrieveOrderEntity(personName, email));
    }

    private Pizza retrievePizzaByName(String name) {
        Specification<Pizza> pizzaSpecification = PizzaSpecificationBuilder.withNameEqualTo(name);
        return extractFirstOrThrowNotFound(pizzaRepository.findAll(pizzaSpecification), ErrorCodes.PIZZA_NOT_FOUND);
    }

    private Status prepareStatusEntityForSave() {
        Status entity = new Status();
        entity.setStatus("In coda");
        return entity;
    }

    private PizzaToOrder preparePizzaToOrderEntityForSave(Integer idOrder, Integer idPizza, Integer idStatus) {
        PizzaToOrder entity = new PizzaToOrder();
        entity.setIdOrder(idOrder);
        entity.setIdPizza(idPizza);
        entity.setIdStatus(idStatus);
        return entity;
    }

}