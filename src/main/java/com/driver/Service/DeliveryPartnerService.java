package com.driver.Service;

import com.driver.DeliveryPartner;
import com.driver.Repository.DeliveryPartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryPartnerService
{
    @Autowired
    DeliveryPartnerRepository deliveryPartnerRepository;
    public void addPartner(String partnerId)
    {
        deliveryPartnerRepository.addPartner(partnerId);
    }

    public DeliveryPartner getPartnerById(String id)
    {
        return deliveryPartnerRepository.getPartnerById(id);
    }
}
