package com.timmy.TimmyRoom.gloabl.error.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE("C01", "Invalid Input Value.", HttpStatus.BAD_REQUEST.value()),
    METHOD_NOT_ALLOWED("C02", "Invalid Method Type.", HttpStatus.METHOD_NOT_ALLOWED.value()),
    ENTITY_NOT_FOUND("C03", "Entity Not Found.", HttpStatus.BAD_REQUEST.value()),
    INTERNAL_SERVER_ERROR("C04", "Internal Server Error Occured.", HttpStatus.INTERNAL_SERVER_ERROR.value()),

    // User
    USER_ACCESS_DENIED("U01", "User Access is Denied.", HttpStatus.UNAUTHORIZED.value()),
    USER_NOT_FOUND("U02", "User is not Found.", HttpStatus.BAD_REQUEST.value()),

    // Token
    INVALID_TOKEN("T01", "Token is Invalid.", HttpStatus.UNAUTHORIZED.value()),

    // File
    FILE_UPLOAD_EXCEPTION("F01", "File Upload is Failed.", HttpStatus.INTERNAL_SERVER_ERROR.value());

    private String code;
    private String message;
    private Integer status;

    ErrorCode(String code, String message, Integer status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }
}
