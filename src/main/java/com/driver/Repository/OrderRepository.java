package com.driver.Repository;

import com.driver.DeliveryPartner;
import com.driver.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository
{
    HashMap<String, Order>orderDB=new HashMap<>();
    HashMap<String, DeliveryPartner>deliveryPartnerDB=new HashMap<>();

    HashMap<String, List<String>>numberOfOrders=new HashMap<>();
    List<String>orders=new ArrayList<>();
    List<String>notAssignedOrders=new ArrayList<>();

    public void addOrder(Order order)
    {
        String id=order.getId();
        orderDB.put(id,order);
        orders.add(id);
        notAssignedOrders.add(id);
    }


}
