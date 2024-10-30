package com.example.exambyte.controllers;
import com.example.exambyte.builder.TestBuilder;
import com.example.exambyte.data.STATUS;
import com.example.exambyte.data.WochenTest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {

    //Controller zur Startseite
    @GetMapping("/")
    public String wochenuebersicht(Model model){
        List<WochenTest> dummies = initDummies();
        model.addAttribute("tests", dummies);
        return "wochenuebersicht";
    }

    //Controller für die Erstellung von Tests
    @GetMapping("/testerstellung")
    public String testerstellung(){
        return "testerstellung";
    }

    //Controller für die Erstellung von Fragen für Tests
    @GetMapping("/testerstellung/fragenerstellung")
    public String fragenerstellung(){
        return "fragenerstellung";
    }

    //Controller für Tests
    @GetMapping("/Woche1")
    public String fragen(){
        return "fragen";
    }

    //Controller für die Korrektur von Fragen
    @GetMapping("/Woche1/frage1_korrektur")
    public String korrekturen(){
        return "korrekturen";
    }

    public List<WochenTest> initDummies(){
        WochenTest dummyTest1 = new TestBuilder()
                .addName("Woche1")
                .addStartTime(LocalDateTime.now())
                .addEndTime(LocalDateTime.now().plusDays(1))
                .addStatus(STATUS.STATUS_AUSSTEHEND)
                .build();

        WochenTest dummyTest2 = new TestBuilder()
                .addName("Woche2")
                .addStartTime(LocalDateTime.now().plusDays(2))
                .addEndTime(LocalDateTime.now().plusDays(3))
                .addStatus(STATUS.STATUS_BEARBEITBAR)
                .build();

        WochenTest dummyTest3 = new TestBuilder()
                .addName("Woche3")
                .addStartTime(LocalDateTime.now().plusDays(4))
                .addEndTime(LocalDateTime.now().plusDays(5))
                .addStatus(STATUS.STATUS_NICHT_BESTANDEN)
                .build();

        return List.of(dummyTest1, dummyTest2, dummyTest3);
    }

}