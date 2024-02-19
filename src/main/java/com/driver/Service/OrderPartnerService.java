package com.driver.Service;

import com.driver.Repository.OrderPartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderPartnerService
{
    @Autowired
    OrderPartnerRepository orderPartnerRepository;

    public void addOrderPartnerPair(String orderId,String partnerId)
    {
        orderPartnerRepository.addOrderPartnerPair(orderId,partnerId);
    }

    public int getOrderCountByPartnerId(String partnerId)
    {
        return orderPartnerRepository.getOrderCountByPartnerId(partnerId);
    }

    public List<String> getOrdersByPartnerId(String partnerId)
    {
        return orderPartnerRepository.getOrdersByPartnerId(partnerId);
    }


    public int getLastDeliveryTimeByPartnerId(String partnerId)
    {
        return orderPartnerRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }
}
