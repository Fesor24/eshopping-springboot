package com.eshopping.project.repositories;

import com.eshopping.project.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}

// CrudRepository
// JpaRepository...kind of extends CrudRepository
