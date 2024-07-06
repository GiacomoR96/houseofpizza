package com.houseofpizza.service;

import static com.houseofpizza.factory.OrderingFactory.buildOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.houseofpizza.model.Order;
import com.houseofpizza.repository.OrderRepository;
import com.houseofpizza.repository.specification.builder.OrderSpecificationBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private PizzaToOrderService pizzaToOrderService;

    @Autowired
    private OrderStatusService orderStatusService;

    // TODO: Add data control logic on id provided in input before saving to database
//    public Long orderCreation(OrderingDto dto) throws ErrorException {
//        log.info("Begin service method orderCreation");
//        List<ProductDto> productDtoList = dto.getProducts();
//
//        checkNotEmptyListOrThrowNotFound(productDtoList, ErrorCodes.LIST_NOT_FOUND);
//        Order order = retrieveOrSaveOrderByPersonName(dto.getPersonName());
//
//        for (ProductDto productDto : productDtoList) {
//            for (var i = 0; i < productDto.getQuantity(); i++) {
//                Pizza pizza = pizzaService.findPizzaByidPizza(productDto.getId());
//                Status status = orderStatusService.saveBaseStatusOrder();
//                pizzaToOrderService.saveBasePizzaToOrder(order.getId(), pizza.getId(), status.getId());
//            }
//        }
//
//        log.info("Service orderId output : {}", order.getId());
//        log.info("End service method orderCreation");
//        return order.getId();
//    }

    private Order retrieveOrSaveOrderByPersonName(String personName) {
        Specification<Order> orderSpecification =
            OrderSpecificationBuilder.withPersonNameEqualTo(personName);

        return repository.findAll(orderSpecification)
            .stream().findFirst()
            .orElseGet(() ->
                repository.save(buildOrder(personName))
            );
    }

}
