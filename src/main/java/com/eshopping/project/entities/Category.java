package com.eshopping.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "categories")
//@Table(name = "category", schema = Schema.Product)
public final class Category extends BaseEntity {
    @NotBlank(message = "Name can not be empty")
    private String name;

    //@OneToMany // specifying this alone will create a categories_products separate entity
    @OneToMany(mappedBy = "category")
    // Now, the class 'Product' will manage this relationship using the 'catageory' property
    private List<Product> products = new ArrayList<Product>();

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId(){
        return this.getId();
    }

    @Override
    public String toString() {
        return "Category info: \n" +
                "Id: " + this.getId().toString() + "\n" +
                "Name: " + this.name;
    }
}
