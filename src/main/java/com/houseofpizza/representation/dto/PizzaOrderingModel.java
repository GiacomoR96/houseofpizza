package com.houseofpizza.representation.dto;

import lombok.Data;

@Data
public class PizzaOrderingModel {

    private String pizzaName;
    private Double price;
    private String status;

}