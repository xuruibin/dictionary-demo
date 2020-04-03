package com.demo.dictionary.common.exception;

import com.demo.dictionary.common.enums.StatusCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Dora B
 */
@Getter
@Setter
public class ValidException extends RuntimeException {

    private static final long serialVersionUID = 1416432693775358424L;

    private int statusCode;

    public ValidException() {
        this(StatusCode.BAD_REQUEST.getRemark());
    }

    public ValidException(String message) {
        super(message);
        this.statusCode = StatusCode.BAD_REQUEST.getCode();
    }
}
