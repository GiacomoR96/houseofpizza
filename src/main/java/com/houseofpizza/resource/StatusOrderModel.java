package com.houseofpizza.resource;

import java.util.List;

import com.houseofpizza.resource.dto.PizzaOrderingDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class StatusOrderModel {

    private Integer orderNumber;
    private List<PizzaOrderingDto> pizzaOrderingDto;

}
