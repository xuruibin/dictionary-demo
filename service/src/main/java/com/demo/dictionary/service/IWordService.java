package com.demo.dictionary.service;

import com.demo.dictionary.module.param.DictionaryParam;
import com.demo.dictionary.module.param.SentenceParam;

import java.util.List;

/**
 * @author Dora B
 */
@SuppressWarnings("JavaDoc")
public interface IWordService {

    /**
     * 获取字典库
     *
     * @param dictionaryType
     * @return
     */
    List<String> listDictionary(int dictionaryType);

    /**
     * 更新字典库
     *
     * @param param
     * @return
     */
    List<String> updateDictionary(DictionaryParam param);

    /**
     * 断句
     *
     * @param param
     * @return
     */
    List<String> wordBreak(SentenceParam param);
}
