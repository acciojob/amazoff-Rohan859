package com.driver.Service;

import com.driver.DeliveryPartner;
import com.driver.Order;
import com.driver.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService
{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    DeliveryPartnerService deliveryPartnerService;
    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    public int getOrderCountByPartnerId(String partnerId) {
        return orderRepository.getOrderCountByPartnerId(partnerId);
    }

    public List<String> getOrderIdsByPartnerId(String partnerId) {
        return orderRepository.getOrderIdsByPartnerId(partnerId);
    }

    public int getCountOfOrdersLeftAfterGivenTimeByPartnerId(String partnerId, int givenTime) {
        return orderRepository.getCountOfOrdersLeftAfterGivenTimeByPartnerId(partnerId, givenTime);
    }

    public Order getOrderById(String orderId) {
        return orderRepository.findById(orderId);
    }
    public List<Order> getOrdersByPartnerId(String partnerId) {
        return orderRepository.getOrdersByPartnerId(partnerId);
    }

    public int convertToMinutes(String time) {
        return orderRepository.convertToMinutes(time);
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        return orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }



    public void unassignOrdersByPartnerId(String partnerId) {
        orderRepository.unassignOrdersByPartnerId(partnerId);
    }
    public void deleteOrderById(String orderId) {
        orderRepository.deleteById(orderId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public void assignOrderToDeliveryPartner(String orderId, String partnerId) {
        Order order = orderRepository.findById(orderId);
        DeliveryPartner deliveryPartner = deliveryPartnerService.getDeliveryPartnerById(partnerId);

        if (order != null && deliveryPartner != null) {
            order.setDeliveryPartner(deliveryPartner);
            orderRepository.save(order);
        }
    }

    public int getCountOfUnassignedOrders() {
        return orderRepository.getCountOfUnassignedOrders();
    }

}
