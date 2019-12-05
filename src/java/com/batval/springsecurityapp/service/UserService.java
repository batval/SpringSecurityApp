package com.batval.springsecurityapp.service;

import com.batval.springsecurityapp.model.User;

/**
 * Service class for {@link User}
 *
 * @author Baturo Valery
 * @version 1.0
 */
public interface UserService {
    void save(User user);
    User findByUserName(String userName);
}
