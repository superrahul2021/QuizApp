package com.quize.quize.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quize.quize.entities.QuestionWrapper;
import com.quize.quize.entities.Response;
import com.quize.quize.services.QuizService;

@RestController
@RequestMapping("quiz") 
public class QuizController {
  @Autowired
  QuizService quizService;  

  // http://localhost:8080/quiz/create?difficultylevel=easy&numQ=5&title=enggQuiz
  @PostMapping("create")
  public ResponseEntity<String> createQuiz(@RequestParam String difficultylevel,@RequestParam int numQ,@RequestParam String title){
    return quizService.createQuiz(difficultylevel,numQ,title);
  }

  @GetMapping("get/{id}")
  public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
       return quizService.getQuizQuestions(id);
  }

  @PostMapping("submit/{id}")
  public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses){
    return quizService.calculateResult(id,responses);
  }
}
