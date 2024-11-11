package com.example.exambyte.WebMvcTests;

import com.example.exambyte.controllers.fragenerstellungController;
import com.example.exambyte.data.WochenTest;
import com.example.exambyte.service.WochenTestService;
import com.example.exambyte.unitTests.WochenTestTestBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(fragenerstellungController.class)
public class fragenerstellungControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WochenTestService testService;

    @Test
    @DisplayName("Die Seite /ExamByte/{testname}/fragenerstellung ist vorhanden")
    void test_1() throws Exception {
        WochenTest woche1 = new WochenTestTestBuilder()
                .addName("woche1").build();
        when(testService.getWochenTests()).thenReturn(List.of(woche1));
        mockMvc.perform(get("/ExamByte/woche1/fragenerstellung"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Auf der Seite /ExamByte/{testname}/fragenerstellung, lässt sich ein Post-Request ohne Inhalt senden")
    void test_2() throws Exception {
        WochenTest woche1 = new WochenTestTestBuilder()
                .addName("woche1").build();
        when(testService.getWochenTests()).thenReturn(List.of(woche1));
        mockMvc.perform(post("/ExamByte/woche1/fragenerstellung"))
                .andExpect(status().isOk());
    }

}
