package com.findalldays.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.findalldays.api.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, String>{

}
