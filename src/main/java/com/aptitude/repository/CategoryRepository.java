package com.aptitude.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aptitude.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
