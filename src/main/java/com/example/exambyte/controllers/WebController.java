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
    public String fragen(){
        return "fragen";
    }

    //Controller für die Korrektur von Fragen
    @GetMapping("/woche1/frage1_korrektur")
    public String korrekturen(){
        return "korrekturen";
    }

}