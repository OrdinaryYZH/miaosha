package com.genericyzh.miaosha.common.exception;


import com.genericyzh.miaosha.common.result.ResultCode;

/**
 * @author genericyzh
 */
public class PublicException extends RuntimeException {
    private int code = 999;
    private String developerMessage;

    public PublicException(String message) {
        super(message);
    }

    public PublicException(ResultCode code) {
        super(code.message());
        this.code = code.code();
    }

    public PublicException(ResultCode code, String message) {
        super(message);
        this.code = code.code();
    }

    public PublicException(int code, String message) {
        super(message);
        this.code = code;
    }

    public PublicException(int code, String message, String developerMessage) {
        super(message);
        this.code = code;
        this.developerMessage = developerMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
