package com.gr1.spring.exception.handler;

import com.gr1.spring.exception.TokenRefreshException;
import org.apache.commons.lang3.StringUtils;
import com.gr1.spring.exception.CustomValidationException;
import com.gr1.spring.exception.EntityNotFoundException;
import com.gr1.spring.exception.error.CustomError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex){
        return buildResponseEntity(new CustomError(HttpStatus.NOT_FOUND,ex));
    }

    @ExceptionHandler(CustomValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleCustomValidation(CustomValidationException ex) {
        CustomError errorResponse = new  CustomError(HttpStatus.BAD_REQUEST, ex);
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setDebugMessage(ex.toString());
        return buildResponseEntity(errorResponse);
    }
    @ExceptionHandler(TokenRefreshException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public  ResponseEntity<Object> handleTokenRefreshException(TokenRefreshException ex) {
        CustomError errorResponse = new  CustomError(HttpStatus.FORBIDDEN, ex);
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setDebugMessage(ex.toString());
        return buildResponseEntity(errorResponse);
    }
    /**
     * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
     *
     * @param ex      the MethodArgumentNotValidException that is thrown when @Valid validation fails
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the CustomError object
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomError errorResponse = new CustomError(HttpStatus.BAD_REQUEST, ex);
        errorResponse.setMessage("Validation error");
        errorResponse.addValidationErrors(ex.getBindingResult().getFieldErrors());
        errorResponse.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleConstraintViolation(
            javax.validation.ConstraintViolationException ex) {
        CustomError errorResponse = new CustomError(HttpStatus.BAD_REQUEST, ex);
        errorResponse.setMessage("Validation error");
        errorResponse.addValidationErrors(ex.getConstraintViolations());
        return buildResponseEntity(errorResponse);
    }


    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        return buildResponseEntity(new CustomError(HttpStatus.BAD_REQUEST, error, ex));
    }

    // unauthorized request
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex) {
        CustomError errorResponse = new CustomError(HttpStatus.UNAUTHORIZED, ex);
        errorResponse.setMessage(ex.getMessage() + ": You are not unauthorized to request http resource");
        errorResponse.setDebugMessage(StringUtils.joinWith("\n", ex.getStackTrace()));
        return buildResponseEntity(errorResponse);
    }
    private ResponseEntity<Object> buildResponseEntity(CustomError customError) {
        return new ResponseEntity<>(customError, customError.getStatus());
    }
}
