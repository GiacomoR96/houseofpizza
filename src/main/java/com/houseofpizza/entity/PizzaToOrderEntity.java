package com.houseofpizza.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "T_PIZZA_TO_ORDER")
@Data
@NoArgsConstructor
@IdClass(PizzaToOrderEntity.class)
public class PizzaToOrderEntity implements Serializable {

    public static final String NUMBER_ORDER_FIELD = "idOrder";
    public static final String ID_PIZZA_FIELD = "idOrder";
    public static final String ID_STATUS_FIELD = "idStatus";

    @Id
    @Column(name = "ID_ORDER")
    private Integer idOrder;

    @Id
    @Column(name = "ID_PIZZA")
    private Integer idPizza;

    @Id
    @Column(name = "ID_STATUS")
    private Integer idStatus;

}
