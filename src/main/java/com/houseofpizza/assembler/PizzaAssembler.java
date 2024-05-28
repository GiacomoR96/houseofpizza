package com.houseofpizza.assembler;

import org.springframework.context.annotation.Scope;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.houseofpizza.controller.OrderingController;
import com.houseofpizza.model.Pizza;
import com.houseofpizza.representation.ProductsModel;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Scope("prototype")
@Component
public class PizzaAssembler extends RepresentationModelAssemblerSupport<Pizza, ProductsModel> {

    public PizzaAssembler() {
        super(OrderingController.class, ProductsModel.class);
    }

    @Override
    @NonNull
    public ProductsModel toModel(@NonNull Pizza entity) {
// TODO : Resolve this problem on mapper
// return PizzaMapper.INSTANCE.entityToModel(entity);
        ProductsModel productsModel = new ProductsModel();

        productsModel.setId(entity.getId());
        productsModel.setName(entity.getName());
        productsModel.setPrice(entity.getPrice());
        productsModel.setImage(entity.getImage());

        return productsModel;
    }

    // TODO : Useful method for supplying the byte array to FE
//    private byte[] populateImageWithMediaType(String image) throws IOException, URISyntaxException {
//        String pathImage = Paths.get("").toAbsolutePath().toString().concat("/images/").concat(image);
//        return Files.readAllBytes(Paths.get(pathImage));
//    }

}
