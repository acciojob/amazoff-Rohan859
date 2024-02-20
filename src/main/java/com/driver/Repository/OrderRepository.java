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

    public DeliveryPartner getPartnerById(String partnerId)
    {
        return deliveryPartnerDB.get(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId)
    {
        if(!partnerListOfOrders.containsKey(partnerId))
        {
            return 0;
        }
        return partnerListOfOrders.get(partnerId).size();
    }

    public List<String>getOrdersByPartnerId(String partnerId)
    {
        return partnerListOfOrders.get(partnerId);
    }

    public List<String>getAllOrders()
    {
        List<String>ans=new ArrayList<>();
        for(String key:orderDB.keySet())
        {
            Order obj=orderDB.get(key);
            ans.add(obj.getId());
        }
        return ans;
    }

    public Integer getCountOfUnassignedOrders()
    {
        return notAssignedOrders.size();
    }

}
