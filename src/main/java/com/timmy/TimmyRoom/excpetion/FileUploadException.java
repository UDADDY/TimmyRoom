package com.timmy.TimmyRoom.excpetion;

import com.timmy.TimmyRoom.gloabl.error.exception.BusinessException;
import com.timmy.TimmyRoom.gloabl.error.exception.ErrorCode;

public class FileUploadException extends BusinessException {
    public FileUploadException() {
        super(ErrorCode.FILE_UPLOAD_EXCEPTION);
    }
}
