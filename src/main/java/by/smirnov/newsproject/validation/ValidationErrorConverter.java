package by.smirnov.newsproject.validation;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static by.smirnov.newsproject.validation.ValidationConstants.ERROR;

public class ValidationErrorConverter {

    public static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + ERROR,
                FieldError::getDefaultMessage
        );

        return bindingResult.getFieldErrors().stream().collect(collector);
    }
}
