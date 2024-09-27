package ru.clevertec.model;


import ru.clevertec.annotation.JsonField;

import java.util.List;

public class Person {

    @JsonField("full_name")
    private String name;
    private int age;
    private boolean married;
    private Address address;
    private List<Hobby> hobbies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", married=" + married +
                ", address=" + address +
                ", hobbies=" + hobbies +
                '}';
    }
}
