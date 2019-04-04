package com.tornado.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Customer {
    private int id;
    private String name;
    private String contactName;
    private String address;
    private String city;
    private String country;
    private String postalCode;
}
