package com.timmy.TimmyRoom.excpetion;

import com.timmy.TimmyRoom.gloabl.error.exception.BusinessException;
import com.timmy.TimmyRoom.gloabl.error.exception.ErrorCode;

public class FileDownloadException extends BusinessException {
    public FileDownloadException() {
        super(ErrorCode.FILE_DOWNLOAD_FAIl);
    }
}
