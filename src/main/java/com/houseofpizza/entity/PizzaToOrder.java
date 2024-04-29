package com.houseofpizza.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "pizza_to_order")
@Getter
@Setter
@NoArgsConstructor
@IdClass(PizzaToOrder.class)
@FieldNameConstants
public class PizzaToOrder implements Serializable {

    @Id
    @Column(name = "id_order")
    private Integer idOrder;

    @Id
    @Column(name = "id_pizza")
    private Integer idPizza;

    @Id
    @Column(name = "id_status")
    private Integer idStatus;

}
