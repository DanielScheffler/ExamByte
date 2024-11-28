package com.example.exambyte.WebMvcTests;

import com.example.exambyte.configuration.MethodSecurityConfig;
import com.example.exambyte.controllers.fragenerstellungController;
import com.example.exambyte.domain.model.FRAGENTYP;
import com.example.exambyte.domain.model.WochenTest;
import com.example.exambyte.helper.WithMockOAuth2User;
import com.example.exambyte.service.WochenTestService;
import com.example.exambyte.unitTests.WochenTestTestBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(fragenerstellungController.class)
@Import(MethodSecurityConfig.class)
public class fragenerstellungControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WochenTestService testService;

    @Test
    @DisplayName("Die Seite /ExamByte/{testname}/fragenerstellung ist für Organisatoren vorhanden")
    @WithMockOAuth2User(roles={"ORGANISATOR"})
    void test_1() throws Exception {
        String testname = "woche1";
        WochenTest woche1 = new WochenTestTestBuilder()
                .addName(testname).build();
        when(testService.getWochenTest(testname)).thenReturn(woche1);
        mockMvc.perform(get("/ExamByte/woche1/fragenerstellung"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Auf der Seite /ExamByte/{testname}/fragenerstellung können Organisatoren einen Post-Request ohne Inhalt senden")
    @WithMockOAuth2User(roles={"ORGANISATOR"})
    void test_2() throws Exception {
        String testname = "woche1";
        WochenTest woche1 = new WochenTestTestBuilder()
                .addName(testname).build();
        when(testService.getWochenTest(testname)).thenReturn(woche1);
        mockMvc.perform(post("/ExamByte/woche1/fragenerstellung").with(csrf()))
                .andExpect(view().name("fragenerstellung"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Auf der Seite /ExamByte/{testname}/fragenerstellung können Organisatoren eine Frage mit dem Typ Freitext erstellen")
    @WithMockOAuth2User(roles={"ORGANISATOR"})
    void test_3() throws Exception {
        String testname = "woche1";
        WochenTest woche1 = new WochenTestTestBuilder()
                .addName(testname).build();
        when(testService.getWochenTest(testname)).thenReturn(woche1);
        String name = "Frage1";
        FRAGENTYP typ = FRAGENTYP.FRAGENTYP_FREITEXT;
        mockMvc.perform(post("/ExamByte/woche1/fragenerstellung")
                        .with(csrf())
                        .param("fragentyp", String.valueOf(typ))
                        .param("name", name)
                        .param("fragestellung", "Test Frage")
                        .param("maxPunktzahl", "10"))
                .andExpect(redirectedUrl("/ExamByte/woche1/Frage1"));
    }

    @Test
    @DisplayName("Auf der Seite /ExamByte/{testname}/fragenerstellung können Organisatoren eine Frage mit dem Typ Multiple Choice erstellen")
    @WithMockOAuth2User(roles="ORGANISATOR")
    void test_4() throws Exception {
        String testname = "woche1";
        WochenTest woche1 = new WochenTestTestBuilder()
                .addName(testname).build();
        when(testService.getWochenTest(testname)).thenReturn(woche1);
        String fragename = "Frage1";
        FRAGENTYP typ = FRAGENTYP.FRAGENTYP_MULTIPLECHOICE;
        mockMvc.perform(post("/ExamByte/woche1/fragenerstellung")
                        .with(csrf())
                        .param("fragentyp", String.valueOf(typ))
                        .param("name", fragename)
                        .param("fragestellung", "Test Frage")
                        .param("maxPunktzahl", "10"))
                .andExpect(redirectedUrl("/ExamByte/woche1/Frage1"));
    }

    @Test
    @DisplayName("Die Seite /ExamByte/{testname}/fragenerstellung ist für alle Rollen außer Organisator forbidden")
    @WithMockOAuth2User(roles={"STUDENT", "KORREKTOR"})
    void test_5() throws Exception {
        String testname = "woche1";
        WochenTest woche1 = new WochenTestTestBuilder()
                .addName(testname).build();
        when(testService.getWochenTest(testname)).thenReturn(woche1);
        mockMvc.perform(get("/ExamByte/woche1/fragenerstellung"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Auf der Seite /ExamByte/{testname}/fragenerstellung kann keine Rolle außer Organisator einen Post-Request ohne Inhalt senden")
    @WithMockOAuth2User(roles={"STUDENT", "KORREKTOR"})
    void test_6() throws Exception {
        String testname = "woche1";
        WochenTest woche1 = new WochenTestTestBuilder()
                .addName(testname).build();
        when(testService.getWochenTest(testname)).thenReturn(woche1);
        mockMvc.perform(post("/ExamByte/woche1/fragenerstellung").with(csrf()))
                .andExpect(status().isForbidden());
    }

}
