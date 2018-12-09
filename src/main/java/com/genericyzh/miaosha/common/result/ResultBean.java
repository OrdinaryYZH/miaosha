/*
 * Copyright 2012 Stormpath, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.genericyzh.miaosha.common.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

/**
 * @author genericyzh
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultBean<T> implements Serializable {
    private final int code;
    private final String message;
    private final String developerMessage;
    private final T data;

    private ResultBean(Builder<T> builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.developerMessage = builder.developerMessage;
        this.data = builder.data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public T getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof ResultBean) {
            ResultBean re = (ResultBean) o;
            return getCode() == re.getCode() &&
                    ObjectUtils.nullSafeEquals(getMessage(), re.getMessage()) &&
                    ObjectUtils.nullSafeEquals(getDeveloperMessage(), re.getDeveloperMessage()) &&
                    ObjectUtils.nullSafeEquals(getData(), re.getData());
        }

        return false;
    }

    @Override
    public int hashCode() {
        //noinspection ThrowableResultOfMethodCallIgnored
        return ObjectUtils.nullSafeHashCode(new Object[]{
                getCode(), getMessage(), getDeveloperMessage(), getData()
        });
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this).toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static class Builder<T> {

        private int code;
        private String message;
        private String developerMessage;
        private T data;

        public Builder() {
        }

        public Builder setCode(int code) {
            this.code = code;
            return this;
        }

        public Builder setCode(ResultCode code) {
            this.code = code.code();
            this.message = code.message();
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setDeveloperMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public Builder setData(T data) {
            this.data = data;
            return this;
        }

        /**
         * 方便客户端DIY
         *
         * @return
         */
        public ResultBean build() {
            return new ResultBean(this);
        }

        //-------------------------------------------------------------------------
        // 以下为重叠构造器，参数较少的时候方便调用
        //-------------------------------------------------------------------------

        public static ResultBean buildOK() {
            return buildOK(ResultCode.SUCCESS.message());
        }

        public static ResultBean buildOK(String message) {
            return buildOK(null, message);
        }

        public static <R> ResultBean buildOK(R data) {
            return buildOK(ResultCode.SUCCESS.message(), data);
        }

        public static <R> ResultBean buildOK(String message, R data) {
            return buildOK(message, data, null);
        }

        public static <R> ResultBean buildOK(String message, R data, String developerMessage) {
            Builder builder = new Builder();
            builder.setCode(ResultCode.SUCCESS)
                    .setData(data)
                    .setMessage(message)
                    .setDeveloperMessage(developerMessage);
            return new ResultBean(builder);
        }

        public static <R> ResultBean buildFAIL() {
            return buildFAIL(ResultCode.FAIL.message());
        }

        public static <R> ResultBean buildFAIL(String message) {
            return buildFAIL(null, message);
        }

        public static <R> ResultBean buildFAIL(R data) {
            return buildFAIL(ResultCode.FAIL.message(), data);
        }

        public static <R> ResultBean buildFAIL(String message, R data) {
            return buildFAIL(message, data, null);
        }

        public static <R> ResultBean buildFAIL(String message, R data, String developerMessage) {
            Builder builder = new Builder();
            builder.setCode(ResultCode.FAIL.code())
                    .setData(data)
                    .setMessage(message)
                    .setDeveloperMessage(developerMessage);
            return new ResultBean(builder);
        }

    }

}