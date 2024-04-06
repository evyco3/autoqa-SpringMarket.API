package org.example.shopspringapi.controllers;

import org.example.shopspringapi.models.Cart;
import org.example.shopspringapi.models.Product;
import org.example.shopspringapi.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private CartService cartService;

    @Autowired
    public void setCartService(CartService cartService){
        this.cartService=cartService;
    }

    @PostMapping
    public ResponseEntity<Cart>createCart(@Valid @RequestBody Cart cart){
        Cart createCart=cartService.createCart(cart);
        return new ResponseEntity<>(createCart, HttpStatus.CREATED);
    }
    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable long cartId) {
        Cart cart = cartService.getCartByID(cartId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<Cart> updateCart(@PathVariable long cartId, @Valid @RequestBody Cart updatedCart) {
        Cart cart = cartService.updateCart(cartId, updatedCart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable long cartId) {
        cartService.deleteCartByID(cartId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{cartId}/products")
    public ResponseEntity<Cart> addProductToCart(@PathVariable long cartId, @Valid @RequestBody Product product) {
        Cart cart = cartService.getCartByID(cartId);
        cart.addProductToList(product);
        cart = cartService.updateCart(cartId, cart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}/products/{productId}")
    public ResponseEntity<Cart> deleteProductFromCart(@PathVariable long cartId, @PathVariable long productId) {
        Cart cart = cartService.getCartByID(cartId);
        cart.getProductList().removeIf(product -> product.getId() == productId);
        cart = cartService.updateCart(cartId, cart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }
}
