package com.eshopping.project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="products")
public class Product extends BaseEntity{
    private String name;

    private String description;

    @Column(name="price", precision = 18, scale=2)
    private BigDecimal price;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}

// Default fetch types
// OneToMany -> Lazy
// ManyToOne -> Eager
// ManyToMany -> Lazy
// OneToOne -> Eager
