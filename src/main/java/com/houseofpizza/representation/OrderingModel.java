package com.houseofpizza.representation;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderingModel extends BaseRepresentationModel<Long, OrderingModel> {

    private Long order;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

}

