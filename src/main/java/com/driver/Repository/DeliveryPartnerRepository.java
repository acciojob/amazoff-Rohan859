package com.driver.Repository;

import com.driver.DeliveryPartner;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DeliveryPartnerRepository
{
    private final Map<String, DeliveryPartner> deliveryPartners = new HashMap<>();

    public void save(DeliveryPartner partner) {
        deliveryPartners.put(partner.getId(), partner);
    }

    public DeliveryPartner findById(String partnerId) {
        return deliveryPartners.get(partnerId);
    }

    public void deleteById(String partnerId) {
        deliveryPartners.remove(partnerId);
    }



}
