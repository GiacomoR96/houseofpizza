package com.houseofpizza.resource.dto;

import lombok.Data;

@Data
public class PizzaOrderingDto {

    private String pizzaName;
    private Double price;
    private String status;

}