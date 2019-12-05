package com.batval.springsecurityapp.validator;

import com.batval.springsecurityapp.model.User;
import com.batval.springsecurityapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for {@link com.batval.springsecurityapp.model.User} class,
 * implements {@link Validator} interface.
 *
 * @author Baturo Valery
 * @version 1.0
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    //Подтверждаем что являемся экземпляром User
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmpty(errors, "userName", "Required");
        if (user.getUsername().length()<6 || user.getUsername().length()>32){
            errors.rejectValue("userName","Size.userForm.userName");
        }
        if (userService.findByUserName(user.getUsername())!=null){
            errors.rejectValue("userName","Duplicate.userForm.userName");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","Required");
        if (user.getPassword().length()<8||user.getPassword().length()>32){
            errors.rejectValue("password", "Size.userForm.password");
        }
        if (!user.getConfirmPassword().equals(user.getPassword())){
            errors.rejectValue("confirmPassword","Different.userForm.password");
        }
    }
}
