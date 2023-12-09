package com.houseofpizza.assembler;

import com.houseofpizza.bin.OrderProcessingBin;
import com.houseofpizza.resource.OrderProcessingModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
