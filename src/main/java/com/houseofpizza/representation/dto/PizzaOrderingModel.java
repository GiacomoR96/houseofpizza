package com.houseofpizza.representation.dto;

import org.springframework.hateoas.RepresentationModel;

import com.houseofpizza.representation.OrderingModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PizzaOrderingModel extends RepresentationModel<OrderingModel> {

    private String name;
    private Double price;
    private String status;

}
