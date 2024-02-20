package com.driver.Service;

import com.driver.DeliveryPartner;
import com.driver.Repository.DeliveryPartnerRepository;
import com.driver.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryPartnerService
{

    @Autowired
    private DeliveryPartnerRepository deliveryPartnerRepository;
    @Autowired
    private OrderRepository orderRepository;

    public void addDeliveryPartner(DeliveryPartner partner) {
        deliveryPartnerRepository.save(partner);
    }

    public DeliveryPartner getDeliveryPartnerById(String partnerId) {
        return deliveryPartnerRepository.findById(partnerId);
    }

    public void deleteDeliveryPartnerById(String partnerId) {
        deliveryPartnerRepository.deleteById(partnerId);
    }

    public void deletePartnerById(String partnerId) {
        deliveryPartnerRepository.deleteById(partnerId);
        orderRepository.reassignOrdersToUnassigned(partnerId);
    }

}
