package com.experiment.sandbox.myfirstapp.managers;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Joe on 3/3/2016.
 */
public class User implements Serializable {

    private int id;
    private String name, username, email;
    private Address address;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

