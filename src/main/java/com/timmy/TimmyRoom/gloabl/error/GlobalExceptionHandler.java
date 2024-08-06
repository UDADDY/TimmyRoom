package com.timmy.TimmyRoom.gloabl.error;

import com.timmy.TimmyRoom.gloabl.error.exception.AccessDeniedException;
import com.timmy.TimmyRoom.gloabl.error.exception.BusinessException;
import com.timmy.TimmyRoom.gloabl.error.exception.EntityNotFoundException;
import com.timmy.TimmyRoom.gloabl.error.exception.ErrorCode;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException exception){
        log.error(exception.getErrorCode().getCode(), exception);

        ErrorCode errorCode = exception.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(errorCode);

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException exception){
        log.error(exception.getErrorCode().getCode(), exception);

        ErrorCode errorCode = exception.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(errorCode);

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception){
        log.error("HttpRequestMethodNotSupportedException", exception);

        ErrorCode errorCode = ErrorCode.METHOD_NOT_ALLOWED;
        ErrorResponse errorResponse = new ErrorResponse(errorCode);

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        log.error("MethodArgumentNotValidException", exception);

        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        ErrorResponse errorResponse = new ErrorResponse(errorCode);

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){
        log.error("HttpMessageNotReadableException", exception);

        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        ErrorResponse errorResponse = new ErrorResponse(errorCode);

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception){
        log.error("MethodArgumentTypeMismatchException", exception);

        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        ErrorResponse errorResponse = new ErrorResponse(errorCode);

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException exception){
        log.error(exception.getErrorCode().getCode(), exception);

        ErrorCode errorCode = exception.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(errorCode);

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(JwtException.class)
    protected ResponseEntity<ErrorResponse> handleJwtException(JwtException exception){
        log.error(exception.getMessage(), exception);

        ErrorCode errorCode = ErrorCode.INVALID_TOKEN;
        ErrorResponse errorResponse = new ErrorResponse(errorCode);

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException exception){
        log.error(exception.getMessage(), exception);

        ErrorCode errorCode = ErrorCode.USER_ACCESS_DENIED;
        ErrorResponse errorResponse = new ErrorResponse(errorCode);

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception exception){
        log.error("Exception", exception);

        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse(errorCode);

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(errorCode.getStatus()));
    }
}
