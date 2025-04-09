package com.eshopping.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class CustomerAddress extends BaseEntity{
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    @OneToOne(mappedBy = "address") // bcos we added this mappedBy, this means, this entity is owned by the Customer entity
    // so basically, Customer entity manages the relationship with the help of the 'address' field specified in its class...
    // hence, a column will not be created here but only in the class that owns it (Customer here)
    private Customer customer;
}
