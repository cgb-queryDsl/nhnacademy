package com.nhnmart.cs.validator;

import com.nhnmart.cs.domain.dto.CustomerPostRegisterRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CustomerPostRegisterValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerPostRegisterRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerPostRegisterRequest request = (CustomerPostRegisterRequest) target;

        String title = request.getTitle();
        if (title.length() > 200) {
            errors.rejectValue("title", "", "title max length is 200");
        }

        String content = request.getContent();
        if (content.length() > 40000) {
            errors.rejectValue("content", "", "content max length is 40000");
        }
    }
}
