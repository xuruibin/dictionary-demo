package com.demo.dictionary.common.response;

import com.demo.dictionary.common.enums.StatusCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Dora B
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("返回信息")
public class ResponseData<T> {

    @ApiModelProperty(value = "状态码 success: 200 fail: 300")
    private int statusCode = 200;

    @ApiModelProperty("提示信息")
    private String message;

    @ApiModelProperty("返回数据")
    private T data;

    @ApiModelProperty("分页数据")
    private PageInfo pageInfo;

    public ResponseData(StatusCode statusCode, T data) {
        this(statusCode.getCode(), statusCode.getRemark(), data, null);
    }

    public ResponseData(String successMessage, T data) {
        this(successMessage, data, null);
    }

    public ResponseData(String successMessage, T data, PageInfo pageInfo) {
        this(StatusCode.OK.getCode(), successMessage, data, pageInfo);
    }

    public ResponseData(String errorMessage) {
        this(StatusCode.FAILED.getCode(), errorMessage);
    }

    public ResponseData(int statusCode, String message) {
        this(statusCode, message, null, null);
    }

    public ResponseData(int statusCode, String message, T data) {
        this(statusCode, message, data, null);
    }

    public ResponseData(StatusCode statusCode) {
        this(statusCode.getCode(), statusCode.getRemark());
    }

}
