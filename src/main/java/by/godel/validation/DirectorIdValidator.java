package by.godel.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DirectorIdValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        String string = (String) target;
        if(target == null || string.equals("")){
            errors.reject("object.null", "Director's id is null");
            return;
        }
        Integer value = Integer.valueOf(string);
        if(value < 1){
            errors.reject("object.less", "Value is less then 1");
        }
    }
}
