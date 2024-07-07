package com.houseofpizza.factory;

import com.houseofpizza.enums.LifecycleEnum;
import com.houseofpizza.mapper.OrderMapper;
import com.houseofpizza.model.Order;

public class OrderFactory {

    public static Order buildBaseOrder(String personName) {
        Order entity = OrderMapper.INSTANCE.toEntity(personName);
        entity.setLifecycle(LifecycleEnum.ACTIVE);
        return entity;
    }

}
