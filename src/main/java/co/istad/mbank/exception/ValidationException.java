package co.istad.mbank.exception;

import co.istad.mbank.base.BaseError;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




@RestControllerAdvice
public class ValidationException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseError<?> handlerException(MethodArgumentNotValidException e){
        List<Map<String,String>>errors =new ArrayList<>();

        for (FieldError fieldError : e.getFieldErrors()){
            Map<String , String>error=new HashMap<>();
            error.put("field",fieldError.getField());
            error.put("message",fieldError.getDefaultMessage());
            errors.add(error);
        }

        return  BaseError.builder()
                .isSuccess(false)
                .message("Validation has Errors")
                .code(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .build();
    }
}
