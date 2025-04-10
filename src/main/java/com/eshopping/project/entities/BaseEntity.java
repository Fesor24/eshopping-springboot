package com.eshopping.project.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

// tells JPA that the field in this class should be mapped to the db columns in the subclasses
// this class will not be treated as an entity...
@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

//    @Override
//    public int hashCode() {
//        return getId().hashCode();
//    }
}

// GenerationType.AUTO -> tells JPA provider to choose the appropriate strategy based on the db...
// if we do not want to worry abt specific ids

// GenerationType.Identity -> not supported by all dbs...mostly rdbms

// Sequence -> uses db sequence to generate primary keys...used with Oracle, Postgres mostly...

// Table -> makes use of a table to determine the next id
