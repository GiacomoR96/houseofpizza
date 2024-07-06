package com.houseofpizza.service;

import org.springframework.stereotype.Service;

import com.houseofpizza.model.Pizza;
import com.houseofpizza.repository.PizzaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PizzaService extends BaseService<PizzaRepository, Pizza, Long> {

    protected PizzaService(PizzaRepository repository) {
        super(repository);
    }

//    public Pizza findPizzaByidPizza(Long id) {
//        Specification<Pizza> pizzaSpecification = PizzaSpecificationBuilder.withIdPizzaEqualTo(id);
//        return repository.findOne(pizzaSpecification)
//            .orElseThrow(() -> generateException(ErrorCodes.PIZZA_NOT_FOUND));
//    }

}
