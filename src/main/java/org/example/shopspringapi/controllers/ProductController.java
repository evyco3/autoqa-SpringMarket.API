package org.example.shopspringapi.controllers;

import org.example.shopspringapi.models.Product;
import org.example.shopspringapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product>createProduct(@Valid @RequestBody Product product){
        Product createProduct=productService.createProduct(product);
        return new ResponseEntity<>(createProduct, HttpStatus.CREATED);
    }
    @PutMapping("{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable long productId,@Valid @RequestBody Product productDetails){
        Product updateProduct=productService.updateProduct(productId,productDetails);
        return new ResponseEntity<>(updateProduct,HttpStatus.OK);
    }

    @DeleteMapping({"productID"})
    public ResponseEntity<?>deleteProduct(@PathVariable long productId){
        productService.deleteProduct(productId);
        return  ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products=productService.getAllProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
    @GetMapping("{productId}")
    public ResponseEntity<Product>getProductById(@PathVariable long productId){
        Product product=productService.getProductByID(productId);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }



}
