package com.houseofpizza.service.impl;

import com.houseofpizza.bin.StatusOrderBin;
import com.houseofpizza.entity.PizzaEntity;
import com.houseofpizza.entity.PizzaToOrderEntity;
import com.houseofpizza.entity.StatusEntity;
import com.houseofpizza.error.ErrorCodes;
import com.houseofpizza.error.ErrorException;
import com.houseofpizza.jpa.PizzaRepository;
import com.houseofpizza.jpa.PizzaToOrderRepository;
import com.houseofpizza.jpa.StatusRepository;
import com.houseofpizza.jpa.specification.builder.PizzaSpecificationBuilder;
import com.houseofpizza.jpa.specification.builder.PizzaToOrderSpecificationBuilder;
import com.houseofpizza.jpa.specification.builder.StatusSpecificationBuilder;
import com.houseofpizza.service.StatusOrderService;
import com.google.common.collect.Iterables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.houseofpizza.error.ErrorException.checkNotEmptyListOrThrowNotFound;

@Service
@Transactional
public class StatusOrderServiceImpl implements StatusOrderService {

    private Logger logger = LoggerFactory.getLogger(StatusOrderServiceImpl.class);

    @Autowired
    private PizzaToOrderRepository pizzaToOrderRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public StatusOrderBin getStatusOrderService(StatusOrderBin statusOrderBin) throws ErrorException {
        logger.info("Begin service method getStatusOrderService");
        Integer orderNumber = statusOrderBin.getOrderNumber();

        List<PizzaToOrderEntity> pizzaToOrderList = retrievePizzaToOrderByOrderNumber(orderNumber);
        Map<PizzaEntity, String> pizzaList = retrievePizzaList(pizzaToOrderList);

        logger.info("Service list output : {}", pizzaList);
        logger.info("End service method getStatusOrderService");
        return StatusOrderBin.builder()
                .orderNumber(orderNumber)
                .pizzaMap(pizzaList)
                .build();
    }

    private List<PizzaToOrderEntity> retrievePizzaToOrderByOrderNumber(Integer orderNumber) throws ErrorException {
        Specification<PizzaToOrderEntity> pizzaToOrderSpecification = PizzaToOrderSpecificationBuilder.withIdOrderEqualTo(orderNumber);
        return checkNotEmptyListOrThrowNotFound(pizzaToOrderRepository.findAll(pizzaToOrderSpecification), ErrorCodes.ERROR03);
    }

    private PizzaEntity retrievePizzaByPizzaId(Integer pizzaId) {
        Specification<PizzaEntity> pizzaSpecification = PizzaSpecificationBuilder.withPizzaIdEqualTo(pizzaId);
        return Optional.of(pizzaRepository.findAll(pizzaSpecification))
                .map(list -> Iterables.getFirst(list, null))
                .get();
    }

    private StatusEntity retrieveStatusByStatusId(Integer statusId) {
        Specification<StatusEntity> pizzaSpecification = StatusSpecificationBuilder.withStatusIdEqualTo(statusId);
        return Optional.of(statusRepository.findAll(pizzaSpecification))
                .map(list -> Iterables.getFirst(list, null))
                .get();
    }

    private Map<PizzaEntity, String> retrievePizzaList(List<PizzaToOrderEntity> pizzaToOrderList) {
        Map<PizzaEntity, String> map = new HashMap<>();

        for (PizzaToOrderEntity pizzaOrder : pizzaToOrderList) {
            PizzaEntity pizzaEntity = retrievePizzaByPizzaId(pizzaOrder.getIdPizza());
            StatusEntity statusEntity = retrieveStatusByStatusId(pizzaOrder.getIdStatus());
            map.put(pizzaEntity, statusEntity.getStatus());
        }
        return map;
    }

}
