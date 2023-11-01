package com.quize.quize.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quize.quize.entities.User;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    User findByUserName(String userName);
    
}
