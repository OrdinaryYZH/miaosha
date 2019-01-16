package com.genericyzh.miaosha.common.controller;

import com.alibaba.fastjson.JSON;
import com.genericyzh.miaosha.common.exception.BusinessException;
import com.genericyzh.miaosha.common.exception.PublicException;
import com.genericyzh.miaosha.common.result.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import static com.genericyzh.miaosha.common.result.ResultCode.FAIL;
import static com.genericyzh.miaosha.common.result.ResultCode.SUCCESS;

/**
 * @author genericyzh
 */
@RestControllerAdvice
public class AdviceController implements ResponseBodyAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdviceController.class);

    @ExceptionHandler(PublicException.class)
    public ResultBean handlerError(BusinessException e) {
        ResultBean resultBean = ResultBean.builder(FAIL).setCode(e.getCode()).setMessage(e.getMessage()).build();
        logInfo(e);
        return resultBean;
    }

    @ExceptionHandler(BindException.class)
    public ResultBean handleBindException(BindException e) {
        // 返回第一个绑定参数错误
        String message = e.getFieldErrors().get(0).getDefaultMessage();
        ResultBean resultBean = ResultBean.builder(FAIL).setMessage(message).build();
        logInfo(e);
        return resultBean;
    }

    @ExceptionHandler(Exception.class)
    public ResultBean handlerError(Exception e) {
        ResultBean resultBean = ResultBean.builder(FAIL).setMessage(e.getMessage()).build();
        logError(e);
        return resultBean;
    }

    private void logInfo(Exception e) {
        LOGGER.info("", e);
    }

    private void logError(Exception e) {
        LOGGER.error("", e);
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (!(body instanceof ResultBean)) {
            ResultBean resultBean = ResultBean.builder(SUCCESS).setData(body).build();
            if (body instanceof String) {
                // 这里不可以去掉
                if (selectedContentType.equals(MediaType.APPLICATION_JSON_UTF8)) {
                    return JSON.toJSONString(resultBean);
                } else if (selectedContentType.equals(MediaType.TEXT_HTML)) {
                    return body;
                }
            }
            return resultBean;
        }
        return body;
    }
}
