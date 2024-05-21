package com.houseofpizza.representation;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductsModel extends RepresentationModel<ProductsModel> {

    private Long id;
    private String name;
    private Double price;
    private String image;

}
