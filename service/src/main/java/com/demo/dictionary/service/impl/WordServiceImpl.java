package com.demo.dictionary.service.impl;

import com.demo.dictionary.common.enums.DictionaryType;
import com.demo.dictionary.common.exception.ValidException;
import com.demo.dictionary.common.util.WordUtil;
import com.demo.dictionary.module.param.DictionaryParam;
import com.demo.dictionary.module.param.SentenceParam;
import com.demo.dictionary.service.IWordService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Dora B
 */
@SuppressWarnings("JavaDoc")
@Service
public class WordServiceImpl implements IWordService {

    @Override
    public List<String> listDictionary(int dictionaryType) {
        dictionaryValid(dictionaryType);
        if (DictionaryType.system.getCode() == dictionaryType) {
            return WordUtil.systemWordDict;
        } else {
            return WordUtil.customWordDict;
        }
    }

    @Override
    public List<String> updateDictionary(DictionaryParam param) {
        dictionaryValid(param.getDictionaryType());
        if (!CollectionUtils.isEmpty(param.getWordDict())) {
            if (DictionaryType.system.getCode() == param.getDictionaryType()) {
                return WordUtil.systemWordDict;
            } else {
                return WordUtil.customWordDict;
            }
        }
        throw new ValidException("参数异常，字典库为空");
    }

    @Override
    public List<String> wordBreak(SentenceParam param) {
        for (Integer dictionaryType : param.getDictionaryTypeList()) {
            dictionaryValid(dictionaryType);
        }
        List<String> sentenceList;
        if (param.getDictionaryTypeList().contains(DictionaryType.system.getCode())) {
            if (param.getDictionaryTypeList().contains(DictionaryType.custom.getCode())) {
                // 根据两个字典库判断
                List<String> wordDict = Stream.of(WordUtil.systemWordDict, WordUtil.customWordDict)
                        .flatMap(Collection::stream).distinct().collect(Collectors.toList());
                sentenceList = WordUtil.wordBreak(param.getSentence(), wordDict);
            } else {
                // 根据系统字典库判断
                sentenceList = WordUtil.wordBreak(param.getSentence(), WordUtil.systemWordDict);
            }
        } else {
            // 根据自定义字典库判断
            sentenceList = WordUtil.wordBreak(param.getSentence(), WordUtil.customWordDict);
        }
        return sentenceList;
    }


    /**
     * 校验字典库类型
     *
     * @param dictionaryType
     */
    private void dictionaryValid(int dictionaryType) {
        if (!DictionaryType.MAP.containsKey(dictionaryType)) {
            throw new ValidException("参数异常，字典类型：" + dictionaryType);
        }
    }
}
