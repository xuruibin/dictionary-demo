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

    @ApiModelProperty("dictionary type: 1-system, 2-custom")
    private int dictionaryType;

    @ApiModelProperty("dictionary")
    private List<String> wordDict;

}
