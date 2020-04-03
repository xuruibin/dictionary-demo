package com.demo.dictionary.module.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Dora B
 */
@Getter
@Setter
public class DictionaryParam {

    @ApiModelProperty("字典类型，1-系统，2-自定义")
    private int dictionaryType;

    @ApiModelProperty("字典库")
    private List<String> wordDict;

}
