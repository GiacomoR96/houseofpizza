package com.houseofpizza.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.houseofpizza.dto.OrderProcessingBin;
import com.houseofpizza.representation.OrderProcessingModel;

@Component
public class OrderProcessingAssembler {

    public OrderProcessingModel populateModel(OrderProcessingBin bin) {
        OrderProcessingModel model = new OrderProcessingModel();
        model.setOrderNumber(populate(bin.getOrderNumber()));
        return model;
    }

    private List<Integer> populate(List<Integer> orderNumber) {
        return new ArrayList<>(orderNumber);
    }

}
