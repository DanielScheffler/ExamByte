package com.example.exambyte.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    //Controller zur Startseite
    @GetMapping("/")
    public String wochenuebersicht(){
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
    @GetMapping("/woche1")
    public String fragenuebersicht(){
        return "fragenuebersicht";
    }

    //Controller für Freitext Fragen
    @GetMapping("/woche1/frage1")
    public String freitext(){
        return "freitext";
    }

    //Controller für MultipleChoice Fragen
    @GetMapping("/woche1/frage2")
    public String multiplechoice(){
        return "multipleChoice";
    }

    //Controller für die Korrektur von Freitext Fragen
    @GetMapping("/woche1/frage1_korrektur")
    public String freitextkorrektur(){
        return "freitext_korrektur";
    }

    //Controller für die Korrektur von MultipleChoice Fragen
    @GetMapping("/woche1/frage2_korrektur")
    public String multiplechoicekorrektur(){
        return "multipleChoice_korrektur";
    }

}