package com.quize.quize.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.quize.quize.dao.UserDao;
import com.quize.quize.entities.User;

@Component
public class UserService {
    @Autowired
    UserDao userDao;
    public ResponseEntity<String> signupUser(String userName, String password) {
        User user=new User();
        user.setUserName(userName);
        user.setPassword(password);
        userDao.save(user);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }


    public ResponseEntity<String> loginUser(String userName, String password) {
        User user=userDao.findByUserName(userName);
        if(user == null){
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }

        if(password.equals(user.getPassword())){
            return new ResponseEntity<>("login success",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Invalid password",HttpStatus.UNAUTHORIZED);
        }
    }
    
}
