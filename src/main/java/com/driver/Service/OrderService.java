package com.driver.Service;

import com.driver.Order;
import com.driver.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService
{
    @Autowired
    OrderRepository orderRepository;
    public void addOrder(Order order)
    {
        orderRepository.addOrder(order);
    }

    public Order getOrderById(String id)
    {
        return orderRepository.getOrderById(id);
    }

    public List<String> getAllOrders()
    {
        return orderRepository.getAllOrders();
    }

    public int getCountOfUnassignedOrders()
    {
        return orderRepository.getCountOfUnassignedOrders();
    }

    public void deletePartnerById(String partnerId)
    {
        orderRepository.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId)
    {
        orderRepository.deleteOrderById(orderId);
    }
}
