package com.houseofpizza.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "T_PIZZA")
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class PizzaEntity implements Serializable {

    public static final String PIZZA_ID_FIELD = "id";
    public static final String NAME_FIELD = "name";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Double price;

}

