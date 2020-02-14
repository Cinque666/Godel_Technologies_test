package by.godel.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DateValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        String strTarget = (String)target;
        if(target == null || strTarget.equals("")){
            errors.reject("object.null", "Date is null");
        }
    }
}
