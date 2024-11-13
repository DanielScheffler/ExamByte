package com.example.exambyte.WebMvcTests;

import com.example.exambyte.controllers.testerstellungController;
import com.example.exambyte.service.WochenTestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebMvcTest(testerstellungController.class)
public class testerstellungControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WochenTestService wochenTestService;

    @Test
    @DisplayName("Get Request auf /ExamByte/testerstellung ist Status:OK")
    void test_1() throws Exception {
        mockMvc.perform(get("/ExamByte/testerstellung"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Post Request auf /ExamByte/Testerstellung mit Validen Parametern wird auf / redirected")
    void test_2() throws Exception {
        String name = "Wochentest1";
        LocalDateTime start = LocalDateTime.MIN;
        LocalDateTime end = LocalDateTime.MAX;

        mockMvc.perform(post("/ExamByte/testerstellung")
                .param("name", name)
                .param("startTime", start.format(DateTimeFormatter.ISO_DATE_TIME))
                .param("endTime", end.format(DateTimeFormatter.ISO_DATE_TIME)))
                .andExpect(redirectedUrl("/"));
    }
    @Test
    @DisplayName("Post Request auf /ExamByte/Testerstellung ohne endTime Parameter wird nicht redirected")
    void test_3() throws Exception {
        LocalDateTime start = LocalDateTime.MIN;

        mockMvc.perform(post("/ExamByte/testerstellung")
                        .param("name", "Wochentest1")
                        .param("startTime", start.format(DateTimeFormatter.ISO_DATE_TIME)))
                .andExpect(status().isOk())
                .andExpect(view().name("testerstellung"));
    }
    @Test
    @DisplayName("PostRequest auf /ExamByte/Testerstellung ohne startTime Parameter wird nicht redirected")
    void test_4() throws Exception {
        LocalDateTime end = LocalDateTime.MAX;

        mockMvc.perform(post("/ExamByte/testerstellung")
                        .param("name", "Wochentest1")
                        .param("endTime", end.format(DateTimeFormatter.ISO_DATE_TIME)))
                .andExpect(status().isOk())
                .andExpect(view().name("testerstellung"));
    }
    @Test
    @DisplayName("PostRequest auf /ExamByte/Testerstellung ohne name wird nicht redirected")
    void test_5() throws Exception {
        LocalDateTime start = LocalDateTime.MIN;
        LocalDateTime end = LocalDateTime.MAX;

        mockMvc.perform(post("/ExamByte/testerstellung")
                        .param("startTime", start.format(DateTimeFormatter.ISO_DATE_TIME))
                        .param("endTime", end.format(DateTimeFormatter.ISO_DATE_TIME)))
                .andExpect(status().isOk())
                .andExpect(view().name("testerstellung"));
    }
    @Test
    @DisplayName("PostRequest auf /ExamByte/testerstellung mit leerem Namen kein redirect")
    void test_6() throws Exception {
        String name = "     ";
        LocalDateTime start = LocalDateTime.MIN;
        LocalDateTime end = LocalDateTime.MAX;

        mockMvc.perform(post("/ExamByte/testerstellung")
                        .param("name", name)
                        .param("startTime", start.format(DateTimeFormatter.ISO_DATE_TIME))
                        .param("endTime", end.format(DateTimeFormatter.ISO_DATE_TIME)))
                .andExpect(status().isOk())
                .andExpect(view().name("testerstellung"));
    }
}
