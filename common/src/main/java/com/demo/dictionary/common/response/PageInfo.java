package com.demo.dictionary.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dora B
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("page data")
public class PageInfo {

    @ApiModelProperty("page num")
    private Integer pageNum;

    @ApiModelProperty("page size")
    private Integer pageSize;

    @ApiModelProperty("total")
    private Long total;

}
