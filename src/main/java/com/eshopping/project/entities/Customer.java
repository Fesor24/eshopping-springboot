package com.eshopping.project.entities;

import jakarta.persistence.*;

@Entity
// the name we specify below to 'name' would be the name of the table
@Table(name="customers", indexes = {
        @Index(name = "idx_email", columnList = "email")
})
public class Customer extends BaseEntity{
    private String firstName;
    private String lastName;
    @Column(name="email", unique = true)
    private String email;
    @OneToOne
    @JoinColumn(name = "address_id")
    // though the default name would be address_id, but we can always specify a name for the column
    // JoinColumn is only used in the owning side to specify the column name...
    private CustomerAddress address;
}
