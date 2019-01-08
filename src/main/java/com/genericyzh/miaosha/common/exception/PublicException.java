package com.genericyzh.miaosha.common.exception;


import com.genericyzh.miaosha.common.result.ResultCode;

/**
 * @author genericyzh
 */
public class PublicException extends RuntimeException {
    private int code = 999;
    private String msg;
    private String developerMessage;

    public PublicException(String message) {
        super(message);
        this.msg = message;
    }

    public PublicException(ResultCode code) {
        super(code.message());
        this.code = code.code();
        this.msg = code.message();
    }

    public PublicException(ResultCode code, String message) {
        super(message);
        this.code = code.code();
        this.msg = message;
    }

    public PublicException(int code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public PublicException(int code, String message, String developerMessage) {
        super(message);
        this.code = code;
        this.msg = message;
        this.developerMessage = developerMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
