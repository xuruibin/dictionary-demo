package com.demo.dictionary.common.enums;

import lombok.Getter;

import java.util.HashMap;

/**
 * @author Dora B
 */
@Getter
public enum StatusCode {

    /**
     * 状态码
     */
    // 成功
    OK(200, "请求成功"),
    CREATED(201, "创建成功"),
    ACCEPTED(202, "更新成功"),

    // 基本异常
    FAILED(300, "失败"),
    BAD_REQUEST	(400, "请求传参异常，客户端根据提示信息修复bug，统一提示系统繁忙"),
    FORBIDDEN(403, "未登录"),
    NOT_FOUND(404, "请求不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器异常，客户端统一提示系统繁忙"),
    ;

    StatusCode(int code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    private int code;

    private String remark;

    private static final HashMap<Integer, StatusCode> MAP = new HashMap<>();

    static {
        for (StatusCode type : StatusCode.values()) {
            if (MAP.put(type.getCode(), type) != null) {
                throw new IllegalArgumentException("duplicate type: " + type.getCode());
            }
        }
    }

    public static StatusCode get(int code) {
        return MAP.get(code);
    }
}
