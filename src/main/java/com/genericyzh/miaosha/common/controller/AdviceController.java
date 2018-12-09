package com.genericyzh.miaosha.common.controller;

import com.alibaba.fastjson.JSON;
import com.genericyzh.miaosha.common.exception.BusinessException;
import com.genericyzh.miaosha.common.result.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author genericyzh
 */
@RestControllerAdvice
public class AdviceController implements ResponseBodyAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdviceController.class);

    @ExceptionHandler(BusinessException.class)
    public ResultBean handlerError(BusinessException e) {
        ResultBean resultBean = ResultBean.Builder.buildFAIL(e.getMessage());
        outputInfoLog(e);
        return resultBean;
    }

    @ExceptionHandler(Exception.class)
    public ResultBean handlerError(Exception e) {
        ResultBean resultBean = ResultBean.Builder.buildFAIL(e.getMessage());
        outputErrorLog(e);
        return resultBean;
    }

    @ExceptionHandler(MultipartException.class)
    public ResultBean handleError1(MultipartException e, RedirectAttributes redirectAttributes) {
        ResultBean resultBean = ResultBean.Builder.buildFAIL(e.getMessage());
        outputErrorLog(e);
        return resultBean;
    }

    private void outputInfoLog(Exception e) {
        LOGGER.info("", e);
    }

    private void outputErrorLog(Exception e) {
        LOGGER.error("", e);
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (!(body instanceof ResultBean)) {
            ResultBean resultBean = ResultBean.Builder.buildOK(body);
            if (body instanceof String) {
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
