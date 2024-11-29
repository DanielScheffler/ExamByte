package com.example.exambyte.WebMvcTests;

import com.example.exambyte.domain.service.builder.FrageBuilder;
import com.example.exambyte.configuration.MethodSecurityConfig;
import com.example.exambyte.controllers.wochentestController;
import com.example.exambyte.domain.model.Frage;
import com.example.exambyte.domain.model.STATUS;
import com.example.exambyte.domain.model.WochenTest;
import com.example.exambyte.helper.WithMockOAuth2User;
import com.example.exambyte.application.service.WochenTestService;
import com.example.exambyte.unitTests.WochenTestTestBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(wochentestController.class)
@Import(MethodSecurityConfig.class)
public class wochentestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WochenTestService wochenTestService;

    @Test
    @DisplayName("Wenn ein leerer Wochentest 'dummyTest' gespeichert ist, dann ist die Route /ExamByte/dummyTest für Studenten vorhanden, falls der Test nicht Ausstehend ist.")
    @WithMockOAuth2User(roles={"STUDENT"})
    void test_1() throws Exception {
        String testName = "dummyTest";
        WochenTest wochenTestStub = new WochenTestTestBuilder()
                .addName(testName)
                .addStatus(STATUS.STATUS_BEARBEITBAR)
                .build();
        when(wochenTestService.getWochenTest(testName)).thenReturn(wochenTestStub);
        mockMvc.perform(get("/ExamByte/"+ testName)).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Wenn ein Wochentest 'dummyTest' mit der Frage 'dummyFrage' gespeichert ist, " +
            "dann ist die Route /ExamByte/dummyTest/dummyFrage für Studenten vorhanden, falls der Test nicht Ausstehend ist.")
    @WithMockOAuth2User(roles={"STUDENT"})
    void test_2() throws Exception {
        String frageName = "dummyFrage";
        Frage frageStub = new FrageBuilder()
                .addName(frageName)
                .build();
        String testName = "dummyTest";
        WochenTest wochenTestStub = new WochenTestTestBuilder()
                .addName(testName)
                .addFrage(frageStub)
                .addStatus(STATUS.STATUS_BEARBEITBAR)
                .build();
        when(wochenTestService.getWochenTest(testName)).thenReturn(wochenTestStub);
        mockMvc.perform(get("/ExamByte/" + testName + "/"+ frageName)).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Die Route /ExamByte/dummyTest ist für USER forbidden")
    @WithMockOAuth2User()
    void test_3() throws Exception {
        String testName = "dummyTest";
        WochenTest wochenTestStub = new WochenTestTestBuilder()
                .addName(testName)
                .build();
        when(wochenTestService.getWochenTest(testName)).thenReturn(wochenTestStub);
        mockMvc.perform(get("/ExamByte/"+ testName))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Die Route /ExamByte/dummyTest/dummyFrage ist für USER forbidden")
    @WithMockOAuth2User()
    void test_4() throws Exception {
        String frageName="dummyFrage";
        Frage frageStub = new FrageBuilder()
                .addName(frageName)
                .build();
        String testName = "dummyTest";
        WochenTest wochenTestStub = new WochenTestTestBuilder()
                .addName(testName)
                .addFrage(frageStub)
                .build();
        when(wochenTestService.getWochenTest(testName)).thenReturn(wochenTestStub);
        mockMvc.perform(get("/ExamByte/"+ testName + "/"+ frageName))
                .andExpect(status().isForbidden());
    }
}
