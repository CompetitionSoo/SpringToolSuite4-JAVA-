package com.mysite.demo;

//리포지터리 
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	Question findById(Integer id);
}
