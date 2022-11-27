package com.example.kisna_app;

public class CropModel {
    private String crop_name,sub_type,price,quantity;

    public CropModel() {
    }

    public CropModel(String crop_name, String sub_type, String price, String quantity) {
        this.crop_name = crop_name;
        this.sub_type = sub_type;
        this.price = price;
        this.quantity = quantity;
    }

    public String getCrop_name() {
        return crop_name;
    }

    public void setCrop_name(String crop_name) {
        this.crop_name = crop_name;
    }

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
