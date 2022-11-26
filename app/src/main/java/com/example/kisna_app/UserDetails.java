package com.example.kisna_app;

import android.widget.EditText;

public class UserDetails {
    private String name,email,phone,adhar_no,state,district,address;

    public UserDetails() {
    }

    public UserDetails(String name, String email, String phone, String adhar_no, String state, String district, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.adhar_no = adhar_no;
        this.state = state;
        this.district = district;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdhar_no() {
        return adhar_no;
    }

    public void setAdhar_no(String adhar_no) {
        this.adhar_no = adhar_no;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
