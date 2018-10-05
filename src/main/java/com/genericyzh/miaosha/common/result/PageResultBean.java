package com.genericyzh.miaosha.common.result;


/**
 * 一般用于controller层返回
 *
 * @author genericyzh
 */
public class PageResultBean<T> {
    private static final long serialVersionUID = 1L;

    public static final int SUCCESS = 0;

    public static final int FAIL = 999;

    public static final int NO_PERMISSION = 2;

    private int code = SUCCESS;

    private String message = "success";
    private PageInfo<T> pageInfo;
    private Object errors;

    private PageResultBean(Builder builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.pageInfo = builder.pageInfo;
        this.errors = builder.errors;
    }

    @Override
    public String toString() {
        return "PagePageResultBean2{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", pageInfo=" + pageInfo +
                ", errors=" + errors +
                "} " + super.toString();
    }

    public int getCode() {
        return this.code;
    }

    public PageResultBean setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public PageResultBean setMessage(String message) {
        this.message = message;
        return this;
    }

    public PageInfo<T> getPageInfo() {
        return this.pageInfo;
    }

    public PageResultBean setPageInfo(PageInfo<T> pageInfo) {
        this.pageInfo = pageInfo;
        return this;
    }


    public Object getErrors() {
        return this.errors;
    }

    public PageResultBean<T> setErrors(Object errors) {
        this.errors = errors;
        return this;
    }

    public static class Builder<T> {

        private int code = SUCCESS;
        private String message = "success";
        private PageInfo<T> pageInfo;
        private Object errors;

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

        public Builder setPageInfo(PageInfo pageInfo) {
            this.pageInfo = pageInfo;
            return this;
        }

        public Builder setErrors(Object errors) {
            this.errors = errors;
            return this;
        }

        public PageResultBean build() {
            return new PageResultBean(this);
        }


        public PageResultBean buildOK() {
            return buildOK(ResultCode.SUCCESS.code(), ResultCode.SUCCESS.message(), null);
        }

        public PageResultBean buildOK(String message) {
            return buildOK(ResultCode.SUCCESS.code(), message, null);
        }

        public PageResultBean buildOK(PageInfo<T> pageInfo) {
            return buildOK(ResultCode.SUCCESS.code(), message, pageInfo);
        }

        public PageResultBean buildOK(int code, String message, PageInfo<T> pageInfo) {
            this.setCode(code)
                    .setPageInfo(pageInfo)
                    .setMessage(message);
            return new PageResultBean(this);
        }

        public PageResultBean buildFAIL() {
            return buildFAIL(ResultCode.FAIL.code(), "fail", null);
        }

        public PageResultBean buildFAIL(Object errors) {
            return buildFAIL(ResultCode.FAIL.code(), "fail", errors);
        }

        public PageResultBean buildFAIL(String message) {
            return buildFAIL(ResultCode.FAIL.code(), message, null);
        }

        public PageResultBean buildFAIL(int code, String message, Object errors) {
            this.setCode(code)
                    .setMessage(message)
                    .setErrors(errors);
            return new PageResultBean(this);
        }

    }

}
