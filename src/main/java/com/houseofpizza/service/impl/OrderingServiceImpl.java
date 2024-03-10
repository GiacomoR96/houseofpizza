package com.houseofpizza.service.impl;

import static com.houseofpizza.error.ErrorException.checkNotEmptyListOrThrowNotFound;
import static com.houseofpizza.error.ErrorException.extractFirstOrThrowNotFound;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.houseofpizza.bin.OrderingBin;
import com.houseofpizza.entity.Order;
import com.houseofpizza.entity.Pizza;
import com.houseofpizza.entity.PizzaToOrder;
import com.houseofpizza.entity.Status;
import com.houseofpizza.error.ErrorCodes;
import com.houseofpizza.error.ErrorException;
import com.houseofpizza.jpa.OrderRepository;
import com.houseofpizza.jpa.PizzaRepository;
import com.houseofpizza.jpa.PizzaToOrderRepository;
import com.houseofpizza.jpa.StatusRepository;
import com.houseofpizza.jpa.specification.builder.OrderSpecificationBuilder;
import com.houseofpizza.jpa.specification.builder.PizzaSpecificationBuilder;
import com.houseofpizza.resource.dto.OrderingDto;
import com.houseofpizza.service.OrderingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class OrderingServiceImpl implements OrderingService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PizzaToOrderRepository pizzaToOrderRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public OrderingBin postOrderingService(OrderingBin bin) throws ErrorException {
        log.info("Begin service method postOrderingService");
        List<OrderingDto> orderingDtoList = bin.getDto().getOrderingDtoList();

        checkNotEmptyListOrThrowNotFound(orderingDtoList, ErrorCodes.ERROR01);
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
        return extractFirstOrThrowNotFound(pizzaRepository.findAll(pizzaSpecification), ErrorCodes.ERROR02);
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