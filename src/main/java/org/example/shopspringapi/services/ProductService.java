package org.example.shopspringapi.services;

import org.example.shopspringapi.models.Product;
import org.example.shopspringapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ProductService  {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public Product createProduct(Product product){
        UUID uuid = UUID.randomUUID();
        long id = uuid.getMostSignificantBits() & Long.MAX_VALUE;
        product.setId(id);
        return productRepository.save(product);
    }

    public Product updateProduct(Long productID,Product productDetails){
        Product product=productRepository.findById(productID)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + productID));
        product.setProductName(productDetails.getProductName());
        product.setPrice(productDetails.getPrice());
        return  productRepository.save(product);
    }
    public void deleteProduct(long productID){
        productRepository.deleteById(productID);
    }
    public Product getProductByID(long productID){
        return  productRepository.findById(productID)
                .orElseThrow(()->new NoSuchElementException("Product not found with id: "+productID));
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

}
