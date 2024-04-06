package org.example.shopspringapi.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collation = "carts")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private List<Product> productList=new ArrayList<>();

    public void addProductToList(Product product){
        productList.add(product);
    }
    public void deleteProductFromList(Product product){
        productList.remove(product);
    }
}
