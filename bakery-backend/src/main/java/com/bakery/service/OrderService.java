package com.bakery.service;

import com.bakery.dto.OrderRequest;
import com.bakery.model.Order;
import com.bakery.model.OrderItem;
import com.bakery.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order createOrder(OrderRequest orderRequest) {
      
        if (orderRequest == null || orderRequest.getItems() == null || orderRequest.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order request or items cannot be empty.");
        }

      
        Order order = new Order();
        order.setUserId(orderRequest.getUserId());
        order.setShippingAddress(orderRequest.getShippingAddress());
        order.setTotalAmount(orderRequest.getTotalAmount());

     
        order.setItems(new ArrayList<>());

       
        for (OrderRequest.OrderItemDto itemDto : orderRequest.getItems()) {
            OrderItem item = new OrderItem();
            item.setProductId(itemDto.getProductId());
            item.setProductName(itemDto.getProductName());
            item.setPrice(itemDto.getPrice());
            item.setQuantity(itemDto.getQuantity());
            item.setOrder(order); 
            order.getItems().add(item);
        }

       
        return orderRepository.save(order);
    }
}
