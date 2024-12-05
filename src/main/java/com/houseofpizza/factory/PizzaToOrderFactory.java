package com.houseofpizza.factory;

import com.houseofpizza.enums.StatusEnum;
import com.houseofpizza.mapper.PizzaToOrderMapper;
import com.houseofpizza.model.Order;
import com.houseofpizza.model.Pizza;
import com.houseofpizza.model.PizzaToOrder;

public class PizzaToOrderFactory {

    public static PizzaToOrder buildBasePizzaToOrder(Order order, Pizza pizza) {
        PizzaToOrder entity = PizzaToOrderMapper.INSTANCE.toEntity(StatusEnum.QUEUE);
        entity.setOrder(order);
        entity.setPizza(pizza);
        return entity;
    }

}

