package com.example.intergalactictrucking.data;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class OrderList {

    private List<List<Order>> orderList;

    public List<List<Order>> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<List<Order>> orderList) {
        this.orderList = orderList;
    }
}
