package org.example.shopspringapi.services;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.example.shopspringapi.models.Cart;
import org.example.shopspringapi.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {
    private CartRepository cartRepository;

    @Autowired
    public void setCartRepository(CartRepository cartRepository){
        this.cartRepository=cartRepository;
    }

    public Cart createCart(Cart cart){
        UUID uuid = UUID.randomUUID();
        long id = uuid.getMostSignificantBits() & Long.MAX_VALUE;
        cart.setId(id);
        return cartRepository.save(cart);

    }

    public Cart updateCart(Long cartID,Cart updateCart){
        Optional<Cart>optionalCart =cartRepository.findById(cartID);
        if(optionalCart.isPresent()){
            Cart cart=optionalCart.get();
            cart.setProductList(updateCart.getProductList());
            return cartRepository.save(cart);
        }else{
            throw new NoSuchElementException("Cart with id " + cartID+ " not found");

        }
    }
    public void deleteCartByID(long cartID){
        try {
            cartRepository.deleteById(cartID);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException("Cart with id " + cartID + " not found");
        }
    }
    public Cart getCartByID(long cartID){
        return  cartRepository.findById(cartID)
                .orElseThrow(()->new NoSuchElementException("Cart not found with id: "+cartID));
    }

    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }


}
