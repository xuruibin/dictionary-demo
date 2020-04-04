package com.demo.dictionary.service;

import com.demo.dictionary.common.exception.ValidException;
import com.demo.dictionary.common.util.WordUtil;
import com.demo.dictionary.module.param.DictionaryParam;
import com.demo.dictionary.module.param.SentenceParam;
import com.demo.dictionary.service.impl.WordServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;

/**
 * @author Dora B
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WordServiceImpl.class)
public class IWordServiceTest {

    @Autowired
    private IWordService wordService;

    @Test
    public void listDictionary() {
        List<String> dictList = wordService.listDictionary(1);
        Assert.assertThat(dictList, is(WordUtil.systemWordDict));

        dictList = wordService.listDictionary(2);
        Assert.assertThat(dictList, is(WordUtil.customWordDict));

        try {
            wordService.listDictionary(3);
        } catch (ValidException ignored) {
        }
    }

    @Test
    public void updateDictionary() {
        DictionaryParam param = new DictionaryParam();
        param.setDictionaryType(1);
        param.setWordDict(WordUtil.systemWordDict);
        List<String> dictList = wordService.updateDictionary(param);
        Assert.assertThat(dictList, is(WordUtil.systemWordDict));

        param.setDictionaryType(2);
        param.setWordDict(WordUtil.customWordDict);
        dictList = wordService.updateDictionary(param);
        Assert.assertThat(dictList, is(WordUtil.customWordDict));

        param.setDictionaryType(2);
        param.setWordDict(new ArrayList<>());
        try {
            wordService.updateDictionary(param);
        } catch (ValidException ignored) {
        }
    }

    @Test
    public void wordBreak() {
        SentenceParam param = new SentenceParam();
        param.setSentence("ilikeicecreammango");
        param.setDictionaryTypeList(Collections.singletonList(1));
        List<String> sentenceList = wordService.wordBreak(param);
        List<String> targetList = Collections.singletonList("i like ice cream man go");
        Assert.assertThat(sentenceList, is(targetList));

        param.setDictionaryTypeList(Collections.singletonList(2));
        sentenceList = wordService.wordBreak(param);
        targetList = Arrays.asList("i like icecream man go", "i like icecream mango");
        Assert.assertThat(sentenceList, is(targetList));

        param.setDictionaryTypeList(Arrays.asList(1, 2));
        sentenceList = wordService.wordBreak(param);
        targetList = Arrays.asList("i like ice cream man go", "i like ice cream mango", "i like icecream man go", "i like icecream mango");
        Assert.assertThat(sentenceList, is(targetList));
    }
}