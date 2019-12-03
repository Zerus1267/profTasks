package com.example.tasks.task_one.remade;

public class Address {
    private String city;
    private String street;
    private String houseNum;

    public Address() {
    }

    public Address(String city, String street, String houseNum) {
        this.city = city;
        this.street = street;
        this.houseNum = houseNum;
    }

    public Address(Address address){
        this.city = address.getCity();
        this.street = address.getStreet();
        this.houseNum = address.getHouseNum();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

}
