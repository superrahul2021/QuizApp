package com.quize.quize.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quize.quize.entities.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer> {
    
}
