package com.eshopping.project.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="products")
public class Product extends BaseEntity{
    private String name;

    private String description;

    @Column(name="price", precision = 18, scale=2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}
