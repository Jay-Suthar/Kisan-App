package com.example.kisna_app;

public class VendorModel {
    private String name_seed,sub_type_seed,price_seed,quantity_seed,parent_vendor;

    public VendorModel() {
    }

    public VendorModel(String name_seed, String sub_type_seed, String price_seed, String quantity_seed,String parent_vendor) {
        this.name_seed = name_seed;
        this.sub_type_seed = sub_type_seed;
        this.price_seed = price_seed;
        this.quantity_seed = quantity_seed;
        this.parent_vendor=parent_vendor;
    }

    public String getName_seed() {
        return name_seed;
    }

    public void setName_seed(String name_seed) {
        this.name_seed = name_seed;
    }

    public String getSub_type_seed() {
        return sub_type_seed;
    }

    public void setSub_type_seed(String sub_type_seed) {
        this.sub_type_seed = sub_type_seed;
    }

    public String getPrice_seed() {
        return price_seed;
    }

    public void setPrice_seed(String price_seed) {
        this.price_seed = price_seed;
    }

    public String getQuantity_seed() {
        return quantity_seed;
    }

    public void setQuantity_seed(String quantity_seed) {
        this.quantity_seed = quantity_seed;
    }

    public String getParent_vendor() {
        return parent_vendor;
    }

    public void setParent_vendor(String parent_vendor) {
        this.parent_vendor = parent_vendor;
    }
}
