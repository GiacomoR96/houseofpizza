package com.houseofpizza.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "T_STATUS")
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class StatusEntity implements Serializable {

    public static final String STATUS_ID_FIELD = "id";
    public static final String STATUS_FIELD = "status";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "STATUS")
    private String status;

}