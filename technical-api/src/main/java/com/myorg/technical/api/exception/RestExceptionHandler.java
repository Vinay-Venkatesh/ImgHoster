package com.myorg.technical.api.exception;

import com.myorg.technical.api.model.ErrorResponse;
import com.myorg.technical.service.entity.UserAuthTokenEntity;
import com.myorg.technical.service.exception.AuthenticationFailedException;
import com.myorg.technical.service.exception.ImageNotFoundException;
import com.myorg.technical.service.exception.UploadFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<ErrorResponse> authenticationFailedException(AuthenticationFailedException exc, WebRequest request) {
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse().code(exc.getCode()).message(exc.getErrorMessage()), HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(UploadFailedException.class)
    public ResponseEntity<ErrorResponse> uploadFailedException(UploadFailedException exc, WebRequest request) {
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse().code(exc.getCode()).message(exc.getErrorMessage()), HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<ErrorResponse> imageNotFoundException(ImageNotFoundException exc, WebRequest request) {
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse().code(exc.getCode()).message(exc.getErrorMessage()), HttpStatus.NOT_FOUND
        );
    }
}
