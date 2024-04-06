package org.example.shopspringapi.services;

import org.example.shopspringapi.models.Cart;
import org.example.shopspringapi.models.Order;
import org.example.shopspringapi.models.Product;
import org.example.shopspringapi.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        double totalAmount = calculateTotalAmount(order.getCart());
        order.setTotalAmount(totalAmount);
        return orderRepository.save(order);
    }

    public Order getOrderById(long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }



    private double calculateTotalAmount(Cart cart) {
        double totalAmount = 0.0;
        for (Product product : cart.getProductList()) {
            totalAmount += product.getPrice();
        }
        return totalAmount;
    }

}