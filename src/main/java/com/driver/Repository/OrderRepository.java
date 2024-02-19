package com.driver.Repository;

import com.driver.Order;
import com.driver.Service.OrderPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository
{
    HashMap<String,Order>orderMap=new HashMap<>(); //id->Order object


    OrderPartnerService orderPartnerService=new OrderPartnerService();


    OrderPartnerRepository orderPartnerRepository=new OrderPartnerRepository();


    DeliveryPartnerRepository deliveryPartnerRepository=new DeliveryPartnerRepository();
    List<String>orders=new ArrayList<>();



    List<String>unassignedOrders=new ArrayList<>();

    public void deletePartnerById(String partnerId)
    {
        //Delete the partnerId
        //And push all his assigned orders to unassigned orders.
        List<String>allOrders=orderPartnerRepository.getDeliveryBoyListOfOrders().get(partnerId);
        for(String val:allOrders)
        {
            unassignedOrders.add(val);
        }

        orderPartnerRepository.getDeliveryBoyListOfOrders().remove(partnerId);
        deliveryPartnerRepository.getDeliveryPartnerMap().remove(partnerId);
    }
    public void addInUnassignedOrders(String ele)
    {
        unassignedOrders.add(ele);
    }
    public void addOrder(Order order)
    {
        String id=order.getId();
        orderMap.put(id,order);
        orders.add(id);
    }

    public Order getOrderById(String id)
    {
        return orderMap.get(id);
    }

    public List<String> getAllOrders()
    {
        return orders;
    }

    public int getCountOfUnassignedOrders()
    {

        int total=0;

        for(String key:orderPartnerRepository.getDeliveryBoyListOfOrders().keySet())
        {
            total+=orderPartnerRepository.getDeliveryBoyListOfOrders().get(key).size();
        }

        return orders.size()-total+unassignedOrders.size();
    }

    public void deleteOrderById(String orderId)
    {
        //Delete an order and also
        // remove it from the assigned order of that partnerId
        String pId=orderPartnerRepository.getOrderIdToPartnerIdMap().get(orderId);
        orderPartnerRepository.getOrderIdToPartnerIdMap().remove(orderId);
        orderMap.remove(orderId);

        List<String>listOfOrders=orderPartnerService.getOrdersByPartnerId(pId);

        listOfOrders.remove(orderId);

    }
}
