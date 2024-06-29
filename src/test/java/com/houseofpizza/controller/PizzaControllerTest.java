package com.houseofpizza.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.houseofpizza.assembler.PizzaProductAssembler;
import com.houseofpizza.model.Pizza;
import com.houseofpizza.representation.ProductsModel;
import com.houseofpizza.service.PizzaService;

@ExtendWith(MockitoExtension.class)
class PizzaControllerTest {

    @Mock
    private PizzaService pizzaService;

    @Mock
    private PizzaProductAssembler pizzaProductAssembler;

    @InjectMocks
    private PizzaController controller;

    @Test
    void getProductsTest() {
        when(pizzaProductAssembler.toCollectionModel(any())).thenReturn(mockCollectionProductsModel());
        given(pizzaService.findAllPizza()).willReturn(populatePizza());

        ResponseEntity<CollectionModel<ProductsModel>> response = controller.getProducts();
        assertNotNull(response);
        CollectionModel<ProductsModel> body = response.getBody();
        assertNotNull(body);
        assertNotNull(body.getContent());
        assertEquals(1, body.getContent().size());
    }

    private List<Pizza> populatePizza() {
        Pizza element1 = new Pizza();
        element1.setId(1L);
        element1.setName("Carbonara");
        element1.setPrice(6.0);
        element1.setImage("carbonara.jpg");

        Pizza element2 = new Pizza();
        element2.setId(2L);
        element2.setName("Margherita");
        element2.setPrice(5.5);
        element2.setImage("margherita.jpg");

        return Arrays.asList(element1, element2);
    }

    private CollectionModel<ProductsModel> mockCollectionProductsModel() {
        return CollectionModel.of(Collections.singletonList(mockProductsModel()));
    }

    private ProductsModel mockProductsModel() {
        ProductsModel productsModel = new ProductsModel();
        productsModel.setName("Margherita");
        productsModel.setPrice(5.5);
        productsModel.setImage("margherita.jpg");
        return productsModel;
    }

}
