package com.project.shopease.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String phone_no;

    private String pincode;

    private String addressLine1;

    private String addressLine2;

    private String landmark;

    private String city;

    private String state;

    private String country;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;



}
