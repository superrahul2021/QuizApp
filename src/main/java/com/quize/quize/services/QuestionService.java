package com.quize.quize.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.quize.quize.dao.QuestionDao;
import com.quize.quize.entities.Questions;

@Component
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Questions>> getAllQuestions() {
        try {
             return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
        } catch (Exception e) {
             e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
       
    }

    public ResponseEntity<List<Questions>> getQuestionBydifficultylevel(String difficultylevel) {
        try {
             return new ResponseEntity<>(questionDao.findByDifficultylevel(difficultylevel),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
       
    }

    public ResponseEntity<String> addQuestion(Questions questions) {
            questionDao.save(questions);
        return new ResponseEntity<>("Question add success...",HttpStatus.CREATED);
       
    }
    
}
