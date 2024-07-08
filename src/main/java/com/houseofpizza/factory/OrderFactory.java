package com.houseofpizza.factory;

import org.apache.commons.lang3.StringUtils;

import com.houseofpizza.enums.LifecycleEnum;
import com.houseofpizza.mapper.OrderMapper;
import com.houseofpizza.model.Order;

public class OrderFactory {

    public static Order buildBaseOrder(String email) {
        Order entity = OrderMapper.INSTANCE.toEntity(email);
        entity.setLifecycle(LifecycleEnum.ACTIVE);
        return entity;
    }

}
