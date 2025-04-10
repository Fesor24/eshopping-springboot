package com.eshopping.project.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "categories")
//@Table(name = "category", schema = Schema.Product)
public final class Category extends BaseEntity {
    @NotBlank(message = "Name can not be empty")
    private String name;

    //@OneToMany // specifying this alone will create a categories_products separate entity
    @OneToMany(mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    // Now, the class 'Product' will manage this relationship using the 'catageory' property
    // CascadeType.PERSIST...when we add the category, the corresponding products if any is also added...
    private List<Product> products = new ArrayList<Product>();

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category info: \n" +
                "Id: " + this.getId().toString() + "\n" +
                "Name: " + this.name;
    }
}


// CascadeType.MERGE -> when the parent entity is merged (i.e updated), the associated child entities are also merged
// CascadeType.Remove -> if we want related entities to be removed when parent entity is removed...
// CascadeType.REFRESH -> when the parent entity is reloaded from db, child entities are refreshed to match DB state...
// CascadeType.DETACH -> when the parent entity is detached from the persistence context, the child entities are detached. They are no longer managed by EntityManager
// CascadeType.ALL -> Applies to all the cascade types...