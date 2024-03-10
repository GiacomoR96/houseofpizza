package com.houseofpizza.assembler;

import org.springframework.stereotype.Component;

import com.houseofpizza.bin.OrderingBin;
import com.houseofpizza.resource.OrderingModel;

@Component
public class OrderingAssembler {

    public OrderingModel populateModel(OrderingBin bin) {
        OrderingModel model = new OrderingModel();
        model.setOrderNumber(bin.getOrderNumber());
        return model;
    }

}
