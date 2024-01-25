package com.example.SchoolManagementApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String address;

    private int no_of_staff;

    public School() {
    }

    public School(String name) {
        this.name = name;
    }

    public School(long id, String name, String address, int no_of_staff) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.no_of_staff = no_of_staff;
    }

    public School(String name, String address, int no_of_staff) {
        this.name = name;
        this.address = address;
        this.no_of_staff = no_of_staff;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNo_of_staff() {
        return no_of_staff;
    }

    public void setNo_of_staff(int no_of_staff) {
        this.no_of_staff = no_of_staff;
    }
}
