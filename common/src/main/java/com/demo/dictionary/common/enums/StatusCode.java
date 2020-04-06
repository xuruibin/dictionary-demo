package com.demo.dictionary.common.enums;

import lombok.Getter;

import java.util.HashMap;

/**
 * @author Dora B
 */
@Getter
public enum StatusCode {

    /**
     * status code
     */
    // success
    OK(200, "request success"),
    CREATED(201, "create success"),
    ACCEPTED(202, "update success"),

    // fail
    FAILED(300, "fail"),
    BAD_REQUEST	(400, "client error: bad request"),
    NOT_FOUND(404, "client error: not found"),
    INTERNAL_SERVER_ERROR(500, "server error"),
    ;

    StatusCode(int code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    private int code;

    private String remark;

    private static final HashMap<Integer, StatusCode> MAP = new HashMap<>();

    static {
        for (StatusCode enums : StatusCode.values()) {
            if (MAP.put(enums.getCode(), enums) != null) {
                throw new IllegalArgumentException("duplicate enums: " + enums.getCode());
            }
        }
    }

    public static StatusCode get(int enums) {
        return MAP.get(enums);
    }
}
