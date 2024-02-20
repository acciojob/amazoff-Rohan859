package com.driver.Repository;

import com.driver.DeliveryPartner;
import com.driver.Order;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class OrderRepository
{
    HashMap<String, Order>orderDB=new HashMap<>();
    HashMap<String, DeliveryPartner>deliveryPartnerDB=new HashMap<>();

    HashMap<String, List<String>>partnerListOfOrders=new HashMap<>(); //partnerId -> {List of orders}
    List<String>orders=new ArrayList<>();
    List<String>notAssignedOrders=new ArrayList<>();

    public void addOrder(Order order)
    {
        String id=order.getId();
        orderDB.put(id,order);
        orders.add(id);
        notAssignedOrders.add(id);
    }

    public void addPartner(String partnerId)
    {
        DeliveryPartner deliveryPartner=new DeliveryPartner(partnerId);
        deliveryPartnerDB.put(partnerId,deliveryPartner);
    }

    public void addOrderPartnerPair(String orderId,String partnerId)
    {
        List<String>order;
        if(!partnerListOfOrders.containsKey(partnerId))
        {
            order=new ArrayList<>();
        }
        else
        {
            order=partnerListOfOrders.get(partnerId);
        }

        order.add(orderId);
        partnerListOfOrders.put(partnerId,order);
        DeliveryPartner deliveryPartner=deliveryPartnerDB.get(partnerId);
        deliveryPartner.setNumberOfOrders(deliveryPartner.getNumberOfOrders()+1);
        deliveryPartnerDB.put(partnerId,deliveryPartner);
        notAssignedOrders.remove(orderId);
    }

    public Order getOrderById(String orderId)
    {
        return orderDB.get(orderId);
    }

}
