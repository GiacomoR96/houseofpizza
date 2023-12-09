package com.houseofpizza.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "T_ORDER")
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class OrderEntity implements Serializable {

    public static final String PERSON_NAME_FIELD = "personName";
    public static final String DATE_FIELD = "date";
    public static final String EMAIL_FIELD = "email";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PERSON_NAME")
    private String personName;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "EMAIL")
    private String email;

}