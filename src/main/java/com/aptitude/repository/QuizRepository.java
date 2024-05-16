package com.aptitude.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aptitude.entity.Category;
import com.aptitude.entity.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
	
    public List<Quiz> findBycategory(Category category);

    public List<Quiz> findByActive(Boolean b);

    public List<Quiz> findByCategoryAndActive(Category c, Boolean b);
}
