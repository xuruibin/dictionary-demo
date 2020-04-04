package com.demo.dictionary.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Dora B
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DictionaryControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;


    @Before
    public void setUp() {
        // 初始化MockMvc对象
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void listDictionary() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/word/dictionary/")
                        .param("dictionaryType", "1")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(200))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateDictionary() throws Exception {
        String json = "{" +
                "\"dictionaryType\":1," +
                "\"wordDict\":[\"i\", \"like\", \"sam\", \"sung\", \"samsung\", \"mobile\", \"ice\", \"cream\", \"man go\"]" +
                "}";
        mvc.perform(MockMvcRequestBuilders.put("/word/dictionary/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(202))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void wordBreak() throws Exception {
        String json = "{" +
                "\"dictionaryTypeList\":[1]," +
                "\"sentence\":\"ilikesamsungmobile\"" +
                "}";
        mvc.perform(MockMvcRequestBuilders.post("/word/break")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(201))
                .andDo(MockMvcResultHandlers.print());
    }
}