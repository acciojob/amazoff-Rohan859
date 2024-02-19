package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order()
    {

    }
    public Order(String id, String deliveryTime)
    {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id=id;

        //deliveryTime = HH:MM format
        //15:4
        String delivery[]=deliveryTime.split(":");
        int actualTime=Integer.parseInt(delivery[0]);
        actualTime=actualTime*60;
        actualTime+=Integer.parseInt(delivery[1]);

        this.deliveryTime=actualTime;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
