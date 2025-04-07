package com.eshopping.project.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

// tells JPA that the field in this class should be mapped to the db columns in the subclasses
// this class will not be treated as an entity...
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected void setId(Long id) {
        this.id = id;
    }

    protected Long getId(){
        return this.id;
    }
}

// GenerationType.AUTO -> tells JPA provider to choose the appropriate strategy based on the db...
// if we do not want to worry abt specific ids

// GenerationType.Identity -> not supported by all dbs...mostly rdbms

// Sequence -> uses db sequence to generate primary keys...used with Oracle, Postgres mostly...

// Table -> makes use of a table to determine the next id
