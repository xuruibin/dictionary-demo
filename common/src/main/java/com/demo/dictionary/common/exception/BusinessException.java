package com.demo.dictionary.common.exception;

import com.demo.dictionary.common.enums.StatusCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Dora B
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 5230930722624049886L;

    private int statusCode;

    public BusinessException() {
        super(StatusCode.INTERNAL_SERVER_ERROR.getRemark());
        this.statusCode = StatusCode.INTERNAL_SERVER_ERROR.getCode();
    }

    public BusinessException(String message) {
        super(message);
        this.statusCode = StatusCode.FAILED.getCode();
    }

    public BusinessException(StatusCode statusCode) {
        super(statusCode.getRemark());
        this.statusCode = statusCode.getCode();
    }
}
