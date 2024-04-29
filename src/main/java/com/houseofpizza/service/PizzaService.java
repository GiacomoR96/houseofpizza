package com.houseofpizza.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.houseofpizza.model.Pizza;
import com.houseofpizza.repository.PizzaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PizzaService {

    @Autowired
    private PizzaRepository repository;

    public List<Pizza> retrieveAllPizza() {
        return repository.findAll();
    }

}
