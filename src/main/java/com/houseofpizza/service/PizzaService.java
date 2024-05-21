package com.houseofpizza.service;


import static com.houseofpizza.exceptions.ErrorException.generateException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.houseofpizza.exceptions.ErrorCodes;
import com.houseofpizza.model.Pizza;
import com.houseofpizza.repository.PizzaRepository;
import com.houseofpizza.repository.specification.builder.PizzaSpecificationBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PizzaService {

    @Autowired
    private PizzaRepository repository;

    public List<Pizza> findAllPizza() {
        log.info("Retrieval of all pizzas");
        return repository.findAll();
    }

    public Pizza findPizzaByidPizza(Long id) {
        Specification<Pizza> pizzaSpecification = PizzaSpecificationBuilder.withIdPizzaEqualTo(id);
        return repository.findOne(pizzaSpecification)
            .orElseThrow(() -> generateException(ErrorCodes.PIZZA_NOT_FOUND));
    }

}
