package com.houseofpizza.representation;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.houseofpizza.representation.dto.PizzaOrderingModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusOrderModel extends RepresentationModel<StatusOrderModel> {

    private List<PizzaOrderingModel> pizzaOrderingModel;

}
