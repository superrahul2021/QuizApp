package com.quize.quize.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quize.quize.entities.Questions;

@Repository
public interface QuestionDao extends JpaRepository<Questions,Integer> {
    List<Questions> findByDifficultylevel( String difficultylevel);

    @Query(value = "SELECT * FROM questions q WHERE q.difficultylevel = :difficultylevel ORDER BY RAND() LIMIT :numQ",nativeQuery = true)
    List<Questions> findRandomQuestionByDifficultylevel(String difficultylevel, int numQ);

   
}
