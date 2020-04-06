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
     * get the dictionary
     *
     * @param dictionaryType
     * @return
     */
    List<String> getDictionary(int dictionaryType);

    /**
     * update the dictionary
     *
     * @param param
     * @return
     */
    List<String> updateDictionary(DictionaryParam param);

    /**
     * sentence break
     *
     * @param param
     * @return
     */
    List<String> wordBreak(SentenceParam param);
}
