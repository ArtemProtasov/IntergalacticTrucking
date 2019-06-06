package com.example.intergalactictrucking.data;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Order {

    private String userId;
    private String fromWhere;
    private String where;
    private String weight;
    private String volume;
    private String bodyType;
    private String shipmentDate;
    private String price;
    private String deliveryInProgress;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFromWhere() {
        return fromWhere;
    }

    public void setFromWhere(String fromWhere) {
        this.fromWhere = fromWhere;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDeliveryInProgress() {
        return deliveryInProgress;
    }

    public void setDeliveryInProgress(String deliveryInProgress) {
        this.deliveryInProgress = deliveryInProgress;
    }
}
