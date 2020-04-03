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
@ApiModel("分页信息")
public class PageInfo {

    @ApiModelProperty("当前页")
    private Integer pageNum;

    @ApiModelProperty("每页的数量")
    private Integer pageSize;

    @ApiModelProperty("总数")
    private Long total;

}
