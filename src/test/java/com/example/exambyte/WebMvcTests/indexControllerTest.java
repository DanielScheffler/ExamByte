package com.example.exambyte.WebMvcTests;

import com.example.exambyte.controllers.indexController;
import com.example.exambyte.data.FRAGENTYP;
import com.example.exambyte.data.STATUS;
import com.example.exambyte.data.WochenTest;
import com.example.exambyte.service.WochenTestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(indexController.class)
public class indexControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WochenTestService wochenTestService;

    @Test
    @DisplayName("Der redirect von / auf /ExamByte funktioniert")
    void test_1() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ExamByte"));
    }

    @Test
    @DisplayName("Der Get Request auf /ExamByte funktioniert ohne Wochentests")
    void test_2() throws Exception {
        when(wochenTestService.getWochenTests()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/ExamByte"))
                .andExpect(view().name("index"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("")
    void  test_3() throws Exception {
        List<WochenTest> testWochenTests = List.of(new WochenTest(new ArrayList<>(), LocalDateTime.MIN, LocalDateTime.MAX, "testWochenTest1", STATUS.STATUS_AUSSTEHEND));
        when(wochenTestService.getWochenTests()).thenReturn(testWochenTests);
        mockMvc.perform(get("/ExamByte"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("tests"))
                .andExpect(model().attribute("tests", testWochenTests));
    }
}
