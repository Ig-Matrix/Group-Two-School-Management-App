package com.example.SchoolManagementApplication.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 3, message = "School name must be at least three characters")
    private String name;

    @NotNull
    @Size(min = 5, message = "Address must be at least five characters")
    private String address;

    @NotNull
    @Min(value = 1, message = "A school must have at least one staff")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof School)) {
            return false;
        }

        School school = (School) o;
        return Objects.equals(this.id, school.id) && Objects.equals(this.name, school.name)
                && Objects.equals(this.address, school.address) && Objects.equals(this.no_of_staff, school.no_of_staff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.address, this.no_of_staff);
    }
}
