package com.houseofpizza.service;

import static com.houseofpizza.factory.OrderFactory.buildBaseOrder;
import static com.houseofpizza.factory.PizzaToOrderFactory.buildBasePizzaToOrder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.houseofpizza.enums.LifecycleEnum;
import com.houseofpizza.exceptions.ErrorCodes;
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

    @Transactional
    public Order createAndSaveOrder(OrderingDto dto) {
        log.info("Begin service method orderCreation");

        Order order = retrieveOrBuildOrder(dto.getEmail());
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

    public Order getStatusOrder(Long id) {
        Order order = findOneOrError404(id);
        if (order.getLifecycle() == LifecycleEnum.DELETED) {
            throw ErrorCodes.generateError404(ErrorCodes.NOT_FOUND);
        }
        return order;
    }

    @Transactional
    public void deleteOrder(Long id) {
        Order order = findOneOrError404(id);
        order.setLifecycle(LifecycleEnum.DELETED);
        save(order);
    }

    private Order retrieveOrBuildOrder(String email) {
        if (StringUtils.isBlank(email)) {
            return buildBaseOrder(email);
        }
        Specification<Order> orderSpecification =
            OrderSpecificationBuilder.withPersonNameEqualToAndOrderActive(email);

        return repository.findOne(orderSpecification)
            .orElseGet(() -> buildBaseOrder(email));
    }

}
