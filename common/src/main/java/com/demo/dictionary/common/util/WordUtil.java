package com.demo.dictionary.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Dora B
 */
@SuppressWarnings("JavaDoc")
public class WordUtil {

    /**
     * 系统字典
     */
    public static List<String> systemWordDict = Arrays.asList("i", "like", "sam", "sung", "samsung", "mobile", "ice", "cream", "man go");
    /**
     * 用户自定义字典
     */
    public static List<String> customWordDict = Arrays.asList("i", "like", "sam", "sung", "mobile", "icecream", "man go", "mango");

    /**
     * 根据字典断句（输出所有有效句子）
     *
     * @param sentence
     * @param wordDict
     * @return
     */
    public static List<String> wordBreak(String sentence, List<String> wordDict) {
        return wordBreak(sentence, sentence.length(), wordDict, "");
    }

    /**
     * 根据字典断句（输出所有有效句子）
     *
     * @param sentence
     * @param length
     * @param wordDict
     * @param result
     * @return
     */
    private static List<String> wordBreak(String sentence, int length, List<String> wordDict, String result) {
        List<String> sentenceList = new ArrayList<>();
        for (int i = 1; i <= length; i++) {
            String prefix = sentence.substring(0, i);
            List<String> prefixList = getDictionaryWord(prefix, wordDict);
            for (String s : prefixList) {
                if (i == length) {
                    String resultWord = result + s;
                    sentenceList.add(resultWord);
                } else {
                    List<String> list = wordBreak(sentence.substring(i, length), length - i, wordDict, result + s + " ");
                    sentenceList.addAll(list);
                }
            }
        }
        return sentenceList;
    }

    /**
     * 检验并获取字典中单词
     *
     * @param word
     * @param wordDict
     * @return
     */
    private static List<String> getDictionaryWord(String word, List<String> wordDict) {
        List<String> wordList = new ArrayList<>();
        for (String dict : wordDict) {
            if (dict.equals(word)) {
                wordList.add(dict);
            } else if (dict.replace(" ", "").equals(word)) {
                wordList.add(dict);
            }
        }
        return wordList;
    }
}
