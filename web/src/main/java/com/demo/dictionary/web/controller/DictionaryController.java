package com.demo.dictionary.web.controller;

import com.demo.dictionary.common.enums.StatusCode;
import com.demo.dictionary.common.response.ResponseData;
import com.demo.dictionary.module.param.DictionaryParam;
import com.demo.dictionary.module.param.SentenceParam;
import com.demo.dictionary.service.IWordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dora B
 */
@Api(tags = "单词服务")
@RestController
@RequestMapping("/word")
public class DictionaryController {

    private final IWordService wordService;

    public DictionaryController(IWordService wordService) {
        this.wordService = wordService;
    }

    @ApiOperation("获取字典库")
    @GetMapping(value = "/dictionary/")
    public ResponseData<List<String>> listDictionary(
            @ApiParam("字典类型，1-系统，2-自定义") @RequestParam int dictionaryType
    ) {
        return new ResponseData<>(StatusCode.OK, wordService.listDictionary(dictionaryType));
    }

    @ApiOperation("更新字典库")
    @PutMapping(value = "/dictionary/")
    public ResponseData<List<String>> updateDictionary(@RequestBody DictionaryParam param) {
        return new ResponseData<>(StatusCode.ACCEPTED, wordService.updateDictionary(param));
    }

    @ApiOperation("断句")
    @PostMapping(value = "/break")
    public ResponseData<List<String>> wordBreak(@RequestBody SentenceParam param) {
        return new ResponseData<>(StatusCode.CREATED, wordService.wordBreak(param));
    }

}
