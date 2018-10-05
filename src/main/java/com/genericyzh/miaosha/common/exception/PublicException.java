package com.genericyzh.miaosha.common.exception;


import com.genericyzh.miaosha.common.result.ResultCode;

/**
 * @author genericyzh
 */
public class PublicException extends RuntimeException {
    private int code;
    private String msg;
    private String developerMessage;

    public PublicException(String message) {
        super(message);
        this.msg = message;
    }

    public PublicException(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }

    public PublicException(ResultCode code, String errorMsg) {
        super(errorMsg);
        this.code = code.code();
        this.msg = errorMsg;
    }

    public PublicException(int code, String errorMsg) {
        this.code = code;
        this.msg = errorMsg;
    }

    public PublicException(int code, String errorMsg, String developerMessage) {
        this.code = code;
        this.msg = errorMsg;
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
