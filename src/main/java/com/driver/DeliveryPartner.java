package com.driver;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.annotation.processing.Generated;


public class DeliveryPartner {

    private String id;
    private int numberOfOrders;


    public DeliveryPartner(String id) {
        this.id = id;
        this.numberOfOrders = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumberOfOrders(){
        return numberOfOrders;
    }

    public void setNumberOfOrders(Integer numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }
}