package com.test.mobiquity.demoproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Atm {

    public Atm(Address address, String type) {
        this.address = address;
        this.type = type;
    }

    public Atm() {
    }

    private Address address;
    private String type;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Atm{" +
                "address=" + address +
                ", type='" + type + '\'' +
                '}';
    }
}