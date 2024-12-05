package com.houseofpizza.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.houseofpizza.model.Pizza;

@Component
@Transactional(readOnly = true)
public interface PizzaRepository extends BaseRepository<Pizza, Long> {
}
