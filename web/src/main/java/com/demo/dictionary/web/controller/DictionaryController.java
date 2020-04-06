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
@Api(tags = "word server")
@RestController
@RequestMapping("/word")
public class DictionaryController {

    private final IWordService wordService;

    public DictionaryController(IWordService wordService) {
        this.wordService = wordService;
    }

    @ApiOperation("get the dictionary")
    @GetMapping(value = "/dictionary/")
    public ResponseData<List<String>> getDictionary(
            @ApiParam("dictionary type: 1-system, 2-custom") @RequestParam int dictionaryType
    ) {
        return new ResponseData<>(StatusCode.OK, wordService.getDictionary(dictionaryType));
    }

    @ApiOperation("update the dictionary")
    @PutMapping(value = "/dictionary/")
    public ResponseData<List<String>> updateDictionary(@RequestBody DictionaryParam param) {
        return new ResponseData<>(StatusCode.ACCEPTED, wordService.updateDictionary(param));
    }

    @ApiOperation("sentence break")
    @PostMapping(value = "/break")
    public ResponseData<List<String>> wordBreak(@RequestBody SentenceParam param) {
        return new ResponseData<>(StatusCode.CREATED, wordService.wordBreak(param));
    }

}
