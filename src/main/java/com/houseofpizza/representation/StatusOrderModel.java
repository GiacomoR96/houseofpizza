package com.houseofpizza.representation;

import com.houseofpizza.representation.BaseRepresentationModel;
import com.houseofpizza.representation.OrderingModel;
import com.houseofpizza.representation.ProductsModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusOrderModel extends BaseRepresentationModel<Long, OrderingModel> {

    private ProductsModel product;
    private String status;

}
