package com.houseofpizza.resource;

import com.houseofpizza.resource.dto.PizzaOrderingDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class StatusOrderModel {

    private Integer orderNumber;
    private List<PizzaOrderingDto> pizzaOrderingDto;

}
