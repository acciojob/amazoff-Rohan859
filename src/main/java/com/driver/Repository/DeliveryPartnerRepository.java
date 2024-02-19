package com.driver.Repository;

import com.driver.DeliveryPartner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class DeliveryPartnerRepository
{
    HashMap<String,DeliveryPartner>deliveryPartnerMap=new HashMap<>(); //id->Delivery Object

    public HashMap<String, DeliveryPartner> getDeliveryPartnerMap() {
        return deliveryPartnerMap;
    }

    public void addPartner(String partnerId)
    {
        DeliveryPartner deliveryPartner=new DeliveryPartner(partnerId);
        deliveryPartnerMap.put(partnerId,deliveryPartner);


    }

    public DeliveryPartner getPartnerById(String id)
    {
        return deliveryPartnerMap.get(id);
    }
}
