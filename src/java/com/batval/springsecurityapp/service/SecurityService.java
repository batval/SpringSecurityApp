package com.batval.springsecurityapp.service;

/**
 * Service for security
 *
 * @author Baturo Valery
 * @version 1.0
 */
public interface SecurityService {
    String findLoggedInUserName();
    void autoLogin(String userName,String password);
}
