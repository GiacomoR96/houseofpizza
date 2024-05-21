package com.houseofpizza.representation;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProcessingModel extends RepresentationModel<OrderProcessingModel> {

    private Long orderNumber;

}
