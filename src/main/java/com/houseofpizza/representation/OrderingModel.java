package com.houseofpizza.representation;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderingModel extends RepresentationModel<OrderingModel> {

    private Long orderNumber;

}
