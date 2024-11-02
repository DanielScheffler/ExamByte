package com.example.exambyte.controllers;
import com.example.exambyte.builder.FrageBuilder;
import com.example.exambyte.builder.TestBuilder;
import com.example.exambyte.data.FRAGETYP;
import com.example.exambyte.data.Frage;
import com.example.exambyte.data.STATUS;
import com.example.exambyte.data.WochenTest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class WebController {

    //Controller zur Startseite
    @GetMapping("/")
    public String wochenuebersicht(Model model){
        List<WochenTest> dummyTests = initDummyTests();
        model.addAttribute("tests", dummyTests);
        return "wochenuebersicht";
    }

    //Controller für die Erstellung von Tests
    @GetMapping("/testerstellung")
    public String testerstellung(Model model){
        List<Frage> dummyFragen = initDummyFragen();
        model.addAttribute("fragen", dummyFragen);
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

    public List<WochenTest> initDummyTests(){
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

    private List<Frage> initDummyFragen(){
        Frage dummyFrage1 = new FrageBuilder()
                .addFragetyp(FRAGETYP.FRAGETYP_FREITEXT)
                .addName("Frage 1")
                .addFragestellung("Warum ist die Banane krumm?")
                .addMaxPunktzahl(2)
                .build();
        Frage dummyFrage2 = new FrageBuilder()
                .addFragetyp(FRAGETYP.FRAGETYP_MULTIPLE_CHOICE)
                .addName("Frage 2")
                .addFragestellung("Was ist 2 + 2?")
                .addMaxPunktzahl(2)
                .addAntwortmöglichkeiten("3 4 5")
                .build();

        return List.of(dummyFrage1, dummyFrage2);
    }

}