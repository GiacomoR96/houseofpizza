package com.houseofpizza.representation;

import lombok.Data;

@Data
public class ProductsModel {

    private Long id;
    private String name;
    private Double price;
    private String image;

}
