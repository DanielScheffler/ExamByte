package com.example.exambyte.WebMvcTests;

import com.example.exambyte.controllers.wochentestController;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(wochentestController.class)
public class wochentestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WochenTestService wochenTestService;

    @Test
    @DisplayName("Wenn ein Wochentest mit dem Namen dummyTest gespeichert ist, und dieser keine Fragen beinhaltet, dann ist die Route /ExamByte/dummyTest vorhanden.")
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
}
