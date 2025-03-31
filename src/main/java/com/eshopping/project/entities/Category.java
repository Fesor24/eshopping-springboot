package com.eshopping.project.entities;

public class Category {
    private Long categoryId;
    private String name;

    public Category() {}

    public Category(Long categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Category info: \n" +
                "Id: " + this.categoryId.toString() + "\n" +
                "Name: " + this.name;
    }
}
