package com.example.anagram.integration;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AnagramApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPostValidAnagram() throws Exception {
        this.mockMvc.perform(post("/anagrams/").content("{\n" +
                        "  \"subject\": \"teacher\",\n" +
                        "  \"anagram\": \"cheater\"\n" +
                        "}").contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"subject\": \"teacher\",\n" +
                        "  \"anagram\": \"cheater\",\n" +
                        "  \"valid\": true }"));
    }

    @Test
    public void testPostInvalidAnagram() throws Exception {
        this.mockMvc.perform(post("/anagrams/").content("{\n" +
                        "  \"subject\": \"invalid\",\n" +
                        "  \"anagram\": \"anagram\"\n" +
                        "}").contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().json("{\n" +
                        "  \"subject\": \"invalid\",\n" +
                        "  \"anagram\": \"anagram\",\n" +
                        "  \"valid\": false }"));
    }

    @Test
    public void testPostInvalidLength() throws Exception {
        this.mockMvc.perform(post("/anagrams/").content("{\n" +
                        "  \"subject\": \"i\",\n" +
                        "  \"anagram\": \"a\"\n" +
                        "}").contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().json("{\"anagram\":\"Anagram should have at least 2 characters\",\"subject\":\"Subject should have at least 2 characters\"}"));
    }

    @Test
    public void testPostEmptyObject() throws Exception {
        this.mockMvc.perform(post("/anagrams/").content("{}").contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().json("{\"anagram\":\"Anagram is required\",\"subject\":\"Subject is required\"}"));
    }

    @Test
    public void testMaxApiLenght() throws Exception {
        String realLongText = RandomStringUtils.random(65535, true, false);
        StringBuilder sb = new StringBuilder(realLongText);
        sb.reverse();
        this.mockMvc.perform(post("/anagrams/").content("{\n" +
                        "  \"subject\": \"" + realLongText + "\",\n" +
                        "  \"anagram\": \"" + sb + "\"\n" +
                        "}").contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"subject\":" + realLongText + ",\n" +
                        "  \"anagram\":" + sb + ",\n" +
                        "  \"valid\": true }"));
    }

}
