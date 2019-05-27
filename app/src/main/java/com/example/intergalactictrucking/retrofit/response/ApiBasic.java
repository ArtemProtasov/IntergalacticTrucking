package com.example.intergalactictrucking.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public
class ApiBasic {

    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("status")
    @Expose
    int status;

    @SerializedName("image")
    @Expose
    String image;

    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("title")
    @Expose
    String title;

    @SerializedName("value")
    @Expose
    String value;
    @SerializedName("order_id")
    @Expose
    String order_id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
