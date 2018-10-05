package com.genericyzh.miaosha.common.exception;


import com.genericyzh.miaosha.common.result.ResultCode;

/**
 * 业务异常，service层抛出
 *
 * @author genericyzh
 */
public class BusinessException extends PublicException {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(ResultCode code) {
        super(code);
    }

    public BusinessException(ResultCode code, String errorMsg) {
        super(code, errorMsg);
    }

    public BusinessException(int code, String errorMsg) {
        super(code, errorMsg);
    }

    public BusinessException(int code, String errorMsg, String developerMessage) {
        super(code, errorMsg, developerMessage);
    }
}
