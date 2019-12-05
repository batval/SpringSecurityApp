package com.batval.springsecurityapp.service;

import com.batval.springsecurityapp.dao.UserDao;
import com.batval.springsecurityapp.model.Role;
import com.batval.springsecurityapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link org.springframework.security.core.userdetails.UserDetailsService} interface
 *
 * @author Baturo Valery
 * @version 1.0
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    //Set roles fof users
    @Override
    @Transactional (readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
     User user = userDao.findByUserName(userName);
     Set<GrantedAuthority> grandAuthorities = new HashSet<>();

     for (Role role: user.getRoles()){
         grandAuthorities.add(new SimpleGrantedAuthority(role.getName()));
     }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),grandAuthorities);
    }
}
