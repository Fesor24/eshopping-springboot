package com.eshopping.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "categories")
//@Table(name = "category", schema = Schema.Product)
public final class Category extends BaseEntity {
    @NotBlank(message = "Name can not be empty")
    private String name;

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
