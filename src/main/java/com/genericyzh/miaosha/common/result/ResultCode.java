package com.genericyzh.miaosha.common.result;

/**
 * 编码意义："0101"，01 子模块，01：描述信息的编号
 * 模块：
 * 00: 公共模块
 * 01：Resource 资源库
 * 02:任务调度
 * 03:日志查询
 *
 * @author genericyzh
 */
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(0, "成功"),

    FAIL(999, "失败"),

    VALID_FAIL(998, "验证失败");

    private ResultCode(int value, String message) {
        this.code = value;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

    private int code;
    private String message;
}