package com.batval.springsecurityapp.dao;

import com.batval.springsecurityapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
    User findByUsername(String userName);
}
