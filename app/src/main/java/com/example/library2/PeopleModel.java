package com.example.library2;

public class PeopleModel {
    private int id;
    private String name, phone;
    public PeopleModel(){


    }
    public PeopleModel(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public PeopleModel(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
