package com.houseofpizza.factory;

import java.time.LocalDate;

import com.houseofpizza.model.Order;

public class OrderingFactory {

    public static Order buildOrder(String personName) {
        Order entity = new Order();
        entity.setPersonName(personName);
        entity.setDate(LocalDate.now());
        return entity;
    }

}
