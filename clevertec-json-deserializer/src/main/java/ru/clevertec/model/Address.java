package ru.clevertec.model;

import ru.clevertec.annotation.JsonField;

public class Address {

    @JsonField(value = "street")
    private String street;

    @JsonField(value = "postal_code")
    private String postalCode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
