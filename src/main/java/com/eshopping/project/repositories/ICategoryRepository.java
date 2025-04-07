package com.eshopping.project.repositories;

import com.eshopping.project.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {

}

// CrudRepository
// JpaRepository...kind of extends CrudRepository
