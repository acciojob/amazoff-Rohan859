package com.driver.Repository;

import com.driver.DeliveryPartner;
import com.driver.Order;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
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

    public void addOrderPartnerPair(String orderId, @RequestParam String partnerId)
    {
        if(!partnerListOfOrders.containsKey(partnerId))
        {
            partnerListOfOrders.put(partnerId,new ArrayList<>());
        }

        partnerListOfOrders.get(partnerId).add(orderId);

        deliveryPartnerDB.get(partnerId).setNumberOfOrders(deliveryPartnerDB.get(partnerId).getNumberOfOrders()+1);

        notAssignedOrders.remove(orderId);
    }

}
