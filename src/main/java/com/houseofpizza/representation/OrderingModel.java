package com.houseofpizza.representation;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderingModel extends BaseRepresentationModel<Long, OrderingModel> {

    private Long order; // TODO: uguale all'id... da mantenere?
    private LocalDate date;

}
