package com.houseofpizza.model;

import jakarta.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@RequiredArgsConstructor
public abstract class BaseEntity<ID> implements IdEntity<ID> {
}
