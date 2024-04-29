package com.houseofpizza.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.houseofpizza.mapper.PizzaMapper;
import com.houseofpizza.model.Pizza;
import com.houseofpizza.representation.ProductsModel;

@Component
public class PizzaAssembler {

    public List<ProductsModel> instantiateModel(List<Pizza> entityList) {
        List<ProductsModel> responseModelList = new ArrayList<>();
        for (Pizza pizza : entityList) {
            ProductsModel responseModel = PizzaMapper.INSTANCE.entityToModel(pizza);
            responseModelList.add(responseModel);
        }

        return responseModelList;
    }

    // TODO : Useful method for supplying the byte array to FE
//    private byte[] populateImageWithMediaType(String image) throws IOException, URISyntaxException {
//        String pathImage = Paths.get("").toAbsolutePath().toString().concat("/images/").concat(image);
//        return Files.readAllBytes(Paths.get(pathImage));
//    }

}
