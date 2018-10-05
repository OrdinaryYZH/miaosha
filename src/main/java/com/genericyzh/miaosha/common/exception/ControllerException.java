package com.genericyzh.miaosha.common.exception;


import com.genericyzh.miaosha.common.result.ResultCode;

/**
 * Controller层抛出
 *
 * @author genericyzh
 */
public class ControllerException extends PublicException {
    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(ResultCode code) {
        super(code);
    }

    public ControllerException(ResultCode code, String errorMsg) {
        super(code, errorMsg);
    }

    public ControllerException(int code, String errorMsg) {
        super(code, errorMsg);
    }

    public ControllerException(int code, String errorMsg, String developerMessage) {
        super(code, errorMsg, developerMessage);
    }
}
