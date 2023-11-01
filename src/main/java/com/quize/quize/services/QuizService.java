package com.quize.quize.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.quize.quize.dao.QuestionDao;
import com.quize.quize.dao.QuizDao;
import com.quize.quize.entities.QuestionWrapper;
import com.quize.quize.entities.Questions;
import com.quize.quize.entities.Quiz;
import com.quize.quize.entities.Response;

@Component
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String difficultylevel, int numQ, String title) {
        List<Questions> questions=questionDao.findRandomQuestionByDifficultylevel(difficultylevel,numQ);
        
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
      Optional<Quiz> quiz=quizDao.findById(id);
      List<Questions> questionsFromDB=quiz.get().getQuestions();
      List<QuestionWrapper> questionsForUser=new ArrayList<>();
      for(Questions q:questionsFromDB){
        QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2());
        questionsForUser.add(qw);
      }

      return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz=quizDao.findById(id).get();
        List<Questions> questions=quiz.getQuestions();
        int right=0;
        int i=0;
        for(Response response:responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
             right++;
         i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }


   
}
