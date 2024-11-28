package com.example.exambyte.WebMvcTests;

import com.example.exambyte.builder.WochenTestBuilder;
import com.example.exambyte.configuration.MethodSecurityConfig;
import com.example.exambyte.controllers.indexController;
import com.example.exambyte.data.STATUS;
import com.example.exambyte.data.WochenTest;
import com.example.exambyte.helper.WithMockOAuth2User;
import com.example.exambyte.service.WochenTestService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.annotation.Secured;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(MethodSecurityConfig.class)
@WebMvcTest(indexController.class)
public class indexControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WochenTestService wochenTestService;

    @Test
    @DisplayName("Die Startseite ist unter / vorhanden und ist für alle Vorhanden")
    @WithMockOAuth2User()
    void test_1() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Der Get Request auf /ExamByte funktioniert ohne Wochentests für Studenten")
    @WithMockOAuth2User(roles = {"STUDENT"})
    void test_2() throws Exception {
        when(wochenTestService.getWochenTests()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/ExamByte"))
                .andExpect(view().name("index"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Der Get Request auf /ExamByte funktioniert mit einem Wochentests, für Studenten")
    @WithMockOAuth2User(roles = {"STUDENT"})
    void  test_3() throws Exception {
        WochenTest test = new WochenTestBuilder().addName("testWochenTest1").build();
        List<WochenTest> testWochenTests = List.of(test);
        when(wochenTestService.getWochenTests()).thenReturn(testWochenTests);
        mockMvc.perform(get("/ExamByte"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("tests"))
                .andExpect(model().attribute("tests", testWochenTests));
    }

    @Test
    @DisplayName("Der Get Request auf /ExamByte funktioniert, bei null Wochentests, für Studenten")
    @WithMockOAuth2User(roles = {"STUDENT"})
    void  test_4() throws Exception {
        when(wochenTestService.getWochenTests()).thenReturn(null);
        mockMvc.perform(get("/ExamByte"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    @DisplayName("Der Get Request auf /ExamByte funktioniert, mit mehreren Wochentests, für Studenten")
    @WithMockOAuth2User(roles = {"STUDENT"})
    void test_5() throws Exception {
        WochenTest test1 = new WochenTestBuilder().addName("testWochenTest1").build();
        WochenTest test2 = new WochenTestBuilder().addName("testWochenTest2").build();
        WochenTest test3 = new WochenTestBuilder().addName("testWochenTest3").build();
        WochenTest test4 = new WochenTestBuilder().addName("testWochenTest4").build();
        List<WochenTest> testWochenTests = List.of(test1, test3, test4, test2);
        when(wochenTestService.getWochenTests()).thenReturn(testWochenTests);
        mockMvc.perform(get("/ExamByte"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("tests"))
                .andExpect(model().attribute("tests", testWochenTests));
    }

    @Test
    @DisplayName("Bei einem Get Request auf /ExamByte ohne Authentifizierung folgt eine Redirection")
    void test_6() throws Exception {
        when(wochenTestService.getWochenTests()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/ExamByte"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Bei einem Get Request auf /ExamByte ohne Authentifizierung folgt eine Redirection")
    @WithMockOAuth2User()
    void test_7() throws Exception {
        when(wochenTestService.getWochenTests()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/ExamByte"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Für Nutzer, die keine Organisatoren sind, wird kein Link zur Testerstellung ausgeliefert.")
    @WithMockOAuth2User(roles= {"STUDENT", "KORREKTOR"})
    void test_8() throws Exception {
        String htmlText = mockMvc.perform(get("/ExamByte"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        Document html = Jsoup.parse(htmlText);
        assertThat(html.select("a[href='/ExamByte/testerstellung']")).isEmpty();
    }

    @Test
    @DisplayName("Für Nutzer, die Organisatoren sind, wird ein Link zur Testerstellung ausgeliefert.")
    @WithMockOAuth2User(roles= {"STUDENT","ORGANISATOR"})
    void test_9() throws Exception {
        String htmlText = mockMvc.perform(get("/ExamByte"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        Document html = Jsoup.parse(htmlText);
        assertThat(html.select("a[href='/ExamByte/testerstellung']")).isNotEmpty();
    }

    @Test
    @DisplayName("Für Nutzer, die keine Organisatoren sind, wird bei ausstehenden Wochentests kein Link zum entsprechenden Test ausgeliefert.")
    @WithMockOAuth2User(roles= {"STUDENT","KORREKTOR"})
    void test_10() throws Exception {
        String testName = "dummyTest";
        WochenTest dummyTest = new WochenTestBuilder()
                .addName(testName)
                .addStatus(STATUS.STATUS_AUSSTEHEND)
                .build();
        when(wochenTestService.getWochenTests()).thenReturn(List.of(dummyTest));
        String htmlText = mockMvc.perform(get("/ExamByte"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        Document html = Jsoup.parse(htmlText);
        assertThat(html.select("a[href='/ExamByte/dummyTest']")).isEmpty();
    }
}
