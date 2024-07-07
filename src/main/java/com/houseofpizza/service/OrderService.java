package com.houseofpizza.service;

import static com.houseofpizza.factory.OrderFactory.buildBaseOrder;
import static com.houseofpizza.factory.PizzaToOrderFactory.buildBasePizzaToOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.houseofpizza.model.Order;
import com.houseofpizza.model.Pizza;
import com.houseofpizza.model.PizzaToOrder;
import com.houseofpizza.repository.OrderRepository;
import com.houseofpizza.repository.specification.builder.OrderSpecificationBuilder;
import com.houseofpizza.representation.dto.OrderingDto;
import com.houseofpizza.representation.dto.ProductDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class OrderService extends BaseService<OrderRepository, Order, Long> {

    @Autowired
    private PizzaService pizzaService;

    protected OrderService(OrderRepository repository) {
        super(repository);
    }

    public Order createAndSaveOrder(OrderingDto dto) {
        log.info("Begin service method orderCreation");

        Order order = retrieveOrBuildOrder(dto.getPersonName());
        List<PizzaToOrder> list = new ArrayList<>();

        for (ProductDto product : dto.getProducts()) {

            for (var i = 0; i < product.getQuantity(); i++) {
                Pizza pizza = pizzaService.findOneOrError404(product.getId());
                list.add(buildBasePizzaToOrder(order, pizza));
            }
        }
        order.getPizzaToOrders().addAll(list);
        save(order);

        log.info("Service orderId output : {}", order);
        log.info("End service method orderCreation");
        return order;
    }

    private Order retrieveOrBuildOrder(String personName) {
        Specification<Order> orderSpecification =
            OrderSpecificationBuilder.withPersonNameEqualToAndOrderActive(personName);

        Optional<Order> entity = repository.findOne(orderSpecification);
        if(entity.isPresent()) {
            return entity.get();
        }
        return buildBaseOrder(personName);
    }

}
