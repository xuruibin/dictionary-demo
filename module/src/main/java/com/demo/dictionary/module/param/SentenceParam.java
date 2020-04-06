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
public class SentenceParam {

    @ApiModelProperty("dictionary type: 1-system, 2-custom")
    private List<Integer> dictionaryTypeList;

    @ApiModelProperty("sentence")
    private String sentence;

}
