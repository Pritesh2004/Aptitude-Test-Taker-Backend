package com.aptitude.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aptitude.entity.Question;
import com.aptitude.entity.Quiz;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
	
    Set<Question> findByQuiz(Quiz quiz);
}
