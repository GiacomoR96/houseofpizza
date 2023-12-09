package com.houseofpizza.service.impl;

import com.houseofpizza.bin.OrderingBin;
import com.houseofpizza.entity.OrderEntity;
import com.houseofpizza.entity.PizzaEntity;
import com.houseofpizza.entity.PizzaToOrderEntity;
import com.houseofpizza.entity.StatusEntity;
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
import com.google.common.collect.Iterables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.houseofpizza.error.ErrorException.checkNotEmptyListOrThrowNotFound;
import static com.houseofpizza.error.ErrorException.extractFirstOrThrowNotFound;

@Service
@Transactional
public class OrderingServiceImpl implements OrderingService {

    private Logger logger = LoggerFactory.getLogger(OrderingServiceImpl.class);

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
        logger.info("Begin service method postOrderingService");
        List<OrderingDto> orderingDtoList = bin.getDto().getOrderingDtoList();

        checkNotEmptyListOrThrowNotFound(orderingDtoList, ErrorCodes.ERROR01);
        OrderEntity order = retrieveOrderByPersonNameAndEmail(bin.getPersonName(), bin.getEmail());

        for (OrderingDto orderingDto : orderingDtoList) {
            PizzaEntity pizza = retrievePizzaByName(orderingDto.getPizzaName());
            StatusEntity status = statusRepository.save(prepareStatusEntityForSave());
            pizzaToOrderRepository.save(preparePizzaToOrderEntityForSave(order.getId(), pizza.getId(), status.getId()));
        }

        logger.info("Service orderId output : {}", order.getId());
        logger.info("End service method postOrderingService");
        return OrderingBin.builder()
                .orderNumber(order.getId())
                .build();
    }

    private OrderEntity prepareOrderEntityForSave(String personName, LocalDate date, String email) {
        OrderEntity entity = new OrderEntity();
        entity.setPersonName(personName);
        entity.setDate(date);
        entity.setEmail(email);
        return entity;
    }

    private OrderEntity saveAndRetrieveOrderEntity(String personName, String email) {
        LocalDate date = LocalDate.now();
        return orderRepository.save(prepareOrderEntityForSave(personName, date, email));
    }

    private OrderEntity retrieveOrderByPersonNameAndEmail(String personName, String email) {
        Specification<OrderEntity> orderSpecification = OrderSpecificationBuilder.withPersonNameAndEmailEqualTo(personName, email);
        return Optional.of(orderRepository.findAll(orderSpecification))
                .map(list -> Iterables.getFirst(list, null))
                .orElseGet(() -> saveAndRetrieveOrderEntity(personName, email));
    }

    private PizzaEntity retrievePizzaByName(String name) {
        Specification<PizzaEntity> pizzaSpecification = PizzaSpecificationBuilder.withNameEqualTo(name);
        return extractFirstOrThrowNotFound(pizzaRepository.findAll(pizzaSpecification), ErrorCodes.ERROR02);
    }

    private StatusEntity prepareStatusEntityForSave() {
        StatusEntity entity = new StatusEntity();
        entity.setStatus("In coda");
        return entity;
    }

    private PizzaToOrderEntity preparePizzaToOrderEntityForSave(Integer idOrder, Integer idPizza, Integer idStatus) {
        PizzaToOrderEntity entity = new PizzaToOrderEntity();
        entity.setIdOrder(idOrder);
        entity.setIdPizza(idPizza);
        entity.setIdStatus(idStatus);
        return entity;
    }

}