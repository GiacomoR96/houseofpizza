package com.houseofpizza.representation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusOrderModel extends BaseRepresentationModel<Long, OrderingModel> {

    private ProductsModel product;
    private String status;

}

