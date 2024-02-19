package com.driver.Repository;

import com.driver.DeliveryPartner;
import com.driver.Order;
import com.driver.Service.DeliveryPartnerService;
import com.driver.Service.OrderService;
import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderPartnerRepository
{
   // HashMap<String,String>orderPartnerMap=new HashMap<>();
    HashMap<String, List<String>>deliveryBoyListOfOrders=new HashMap<>(); //partnerId-> list of orders

    HashMap<String,String>orderIdToPartnerIdMap=new HashMap<>(); //oederId -> partnerId

    @Autowired
    OrderService orderService;

    @Autowired
    DeliveryPartnerService deliveryPartnerService;

    public int getLastDeliveryTimeByPartnerId(String partnerId)
    {
       //which has more time it will be delivered at last
       List<String>ordersIds=deliveryBoyListOfOrders.get(partnerId);
       int time=0;
       for(String id:ordersIds)
       {
           Order myOrder=orderService.getOrderById(id);
           if(time<myOrder.getDeliveryTime())
           {
               time=myOrder.getDeliveryTime();
           }
       }
       return time;
    }

    public HashMap<String, String> getOrderIdToPartnerIdMap()
    {
        return orderIdToPartnerIdMap;
    }

    public HashMap<String, List<String>> getDeliveryBoyListOfOrders() {
        return deliveryBoyListOfOrders;
    }

    public void addOrderPartnerPair(String orderId, String partnerId)
    {
//        orderPartnerMap.put(orderId,partnerId);
//        DeliveryPartner deliveryPartner=deliveryPartnerService.getPartnerById(partnerId);
//        deliveryPartner.setNumberOfOrders(deliveryPartner.getNumberOfOrders()+1);

        if(!deliveryBoyListOfOrders.containsKey(partnerId))
        {
            deliveryBoyListOfOrders.put(partnerId,new ArrayList<>());
        }

        deliveryBoyListOfOrders.get(partnerId).add(orderId);
        orderIdToPartnerIdMap.put(orderId,partnerId);
    }

    public int getOrderCountByPartnerId(String partnerId)
    {
        if(!deliveryBoyListOfOrders.containsKey(partnerId))
        {
            deliveryBoyListOfOrders.put(partnerId,new ArrayList<>());
            return 0;//no orders present
        }

        return deliveryBoyListOfOrders.get(partnerId).size();
    }

    public List<String> getOrdersByPartnerId(String partnerId)
    {
        if(!deliveryBoyListOfOrders.containsKey(partnerId))
        {
            deliveryBoyListOfOrders.put(partnerId,new ArrayList<>());
            return new ArrayList<>();
        }

        return deliveryBoyListOfOrders.get(partnerId);
    }
}
