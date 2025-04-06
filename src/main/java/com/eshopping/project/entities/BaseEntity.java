package com.eshopping.project.entities;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

// tells JPA that the field in this class should be mapped to the db columns in the subclasses
// this class will not be treated as an entity...
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    protected Long id;

    protected void setId(Long id) {
        id = id;
    }

    protected Long getId(){
        return this.id;
    }
}
