package com.houseofpizza.representation;

import java.util.List;

import com.houseofpizza.representation.dto.PizzaOrderingModel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class StatusOrderModel {

    private Integer orderNumber;
    private List<PizzaOrderingModel> pizzaOrderingModel;

}
