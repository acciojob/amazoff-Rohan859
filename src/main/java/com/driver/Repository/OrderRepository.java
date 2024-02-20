package com.driver.Repository;

import com.driver.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class OrderRepository
{
    private final Map<String, Order> orders = new HashMap<>();

    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    public Order findById(String orderId) {
        return orders.get(orderId);
    }

    public List<Order> getOrdersByPartnerId(String partnerId) {
        return orders.values().stream()
                .filter(order -> order.getDeliveryPartner() != null && order.getDeliveryPartner().getId().equals(partnerId))
                .collect(Collectors.toList());
    }

    public void deleteById(String orderId) {
        orders.remove(orderId);
    }

    public void unassignOrdersByPartnerId(String partnerId) {
        orders.values().stream()
                .filter(order -> order.getDeliveryPartner() != null && order.getDeliveryPartner().getId().equals(partnerId))
                .forEach(order -> order.setDeliveryPartner(null));
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    public int getCountOfUnassignedOrders() {
        return (int) orders.values().stream()
                .filter(order -> order.getDeliveryPartner() == null)
                .count();
    }

    public int getOrderCountByPartnerId(String partnerId) {
        return (int) orders.values().stream()
                .filter(order -> order.getDeliveryPartner() != null && order.getDeliveryPartner().getId().equals(partnerId))
                .count();
    }

    public List<String> getOrderIdsByPartnerId(String partnerId) {
        return orders.values().stream()
                .filter(order -> order.getDeliveryPartner() != null && order.getDeliveryPartner().getId().equals(partnerId))
                .map(Order::getId)
                .collect(Collectors.toList());
    }

    public int getCountOfOrdersLeftAfterGivenTimeByPartnerId(String partnerId, int givenTime) {
        return (int) orders.values().stream()
                .filter(order -> order.getDeliveryPartner() != null && order.getDeliveryPartner().getId().equals(partnerId))
                .filter(order -> order.getDeliveryTime() > givenTime)
                .count();
    }

    public int convertToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        List<Order> partnerOrders = orders.values().stream()
                .filter(order -> order.getDeliveryPartner() != null && order.getDeliveryPartner().getId().equals(partnerId))
                .collect(Collectors.toList());

        if (!partnerOrders.isEmpty()) {
            // Assuming that 'deliveryTime' is an integer representing time in minutes
            int lastDeliveryTime = partnerOrders.stream()
                    .mapToInt(Order::getDeliveryTime)
                    .max()
                    .orElse(0);

            // Convert lastDeliveryTime to HH:MM format
            int hours = lastDeliveryTime / 60;
            int minutes = lastDeliveryTime % 60;
            return String.format("%02d:%02d", hours, minutes);
        } else {
            return null; // No orders for the given partner
        }
    }



    public void reassignOrdersToUnassigned(String partnerId) {
        List<Order> partnerOrders = getOrdersByPartnerId(partnerId);
        for (Order order : partnerOrders) {
            order.setDeliveryPartner(null); // Set delivery partner to null (unassigned)
            save(order);
        }
    }

}
