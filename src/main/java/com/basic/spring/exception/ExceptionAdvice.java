package com.basic.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND) // http status 404
    String handlerProductNotFound(ProductNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // http status 500
    String handlerStorageException(StorageException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST) // http status 500
    String handlerMaxUploadException(MaxUploadSizeExceededException ex) {
        return "Maximum upload size exceeded";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST) // http status 500
    String handlerValidateException(ValidationException ex) {
        return ex.getMessage();
    }
}
