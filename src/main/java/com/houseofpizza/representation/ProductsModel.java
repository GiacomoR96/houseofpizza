package com.houseofpizza.representation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductsModel extends BaseRepresentationModel<Long, ProductsModel> {

    private String name;
    private Double price;
    private String image;

}

