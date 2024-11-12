package com.example.exambyte.WebMvcTests;

import com.example.exambyte.builder.FrageBuilder;
import com.example.exambyte.controllers.wochentestController;
import com.example.exambyte.data.Frage;
import com.example.exambyte.data.STATUS;
import com.example.exambyte.data.WochenTest;
import com.example.exambyte.service.WochenTestService;
import com.example.exambyte.unitTests.WochenTestTestBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(wochentestController.class)
public class wochentestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WochenTestService wochenTestService;

    @Test
    @DisplayName("Wenn ein leerer Wochentest 'dummyTest' gespeichert ist, dann ist die Route /ExamByte/dummyTest vorhanden.")
    void test_1() throws Exception {

        //Test stubben
        WochenTest wochenTestStub = new WochenTestTestBuilder()
                .addName("dummyTest")
                .build();

        //WochenTestService mocken
        when(wochenTestService.getWochenTests()).thenReturn(List.of(wochenTestStub));

        //Assertion
        mockMvc.perform(get("/ExamByte/dummyTest")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Wenn ein Wochentest 'dummyTest' mit der Frage 'dummyFrage' gespeichert ist, " +
            "dann ist die Route /ExamByte/dummyTest/dummyFrage vorhanden.")
    void test_2() throws Exception {

        //Frage stubben
        Frage frageStub = new FrageBuilder()
                .addName("dummyFrage")
                .build();

        //Test stubben
        WochenTest wochenTestStub = new WochenTestTestBuilder()
                .addName("dummyTest")
                .addFrage(frageStub)
                .build();

        //Service mocken
        when(wochenTestService.getWochenTests()).thenReturn(List.of(wochenTestStub));

        //Assertion
        mockMvc.perform(get("/ExamByte/dummyTest/dummyFrage")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Wenn ein Wochentest 'dummyTest' gesucht wird, der nicht gespeichert ist, dann ist das Routing auf" +
            "/Exambyte/dummyTest vorhanden, und im Model ist das 'error' Attribut gesetzt.")
    void test_3() throws Exception {
        when(wochenTestService.getWochenTests()).thenReturn(List.of());
        mockMvc.perform(get("/ExamByte/dummyTest"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("error"));
    }
}
