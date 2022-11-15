package com.nhnacademy.springmvc.validator;

import com.nhnacademy.springmvc.domain.StudentRequestModify;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StudentRegisterModifyValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return StudentRequestModify.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        checkEmpty(errors);

        StudentRequestModify request = (StudentRequestModify) target;

        String email = request.getEmail();
        if (!email.contains("@")) {
            errors.rejectValue("email", "", "email form is not correct");
        }

        int score = request.getScore();
        if (score < 0 || score > 100) {
            errors.rejectValue("score", "", "score is over range");
        }

        String content = request.getComment();
        if (content.length() > 200) {
            errors.rejectValue("comment", "", "content max length is 100");
        }
    }

    private static void checkEmpty(Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "name is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "email is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "score", "", "score is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comment", "", "comment is empty");
    }
}
