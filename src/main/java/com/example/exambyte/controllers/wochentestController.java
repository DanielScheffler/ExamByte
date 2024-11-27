package com.example.exambyte.controllers;

import com.example.exambyte.data.Frage;
import com.example.exambyte.data.WochenTest;
import com.example.exambyte.service.WochenTestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class wochentestController {

    private final WochenTestService wochenTestService;

    public wochentestController(WochenTestService wochenTestService) {
        this.wochenTestService = wochenTestService;
    }

    //Handler für einen Test ohne Fragen
    @GetMapping("/ExamByte/{testname}")
    public String fragenLeer(Model model, @PathVariable String testname){
        WochenTest wochenTest = wochenTestService.getWochenTest(testname);
        model.addAttribute("test", wochenTest);
        return "wochentest";
    }

    //Controller für Tests
    @GetMapping("/ExamByte/{testname}/{fragename}")
    public String fragen(Model model,
                         @PathVariable String testname,
                         @PathVariable String fragename){
        WochenTest wochenTest = wochenTestService.getWochenTest(testname);
        Frage frage = wochenTest.getFrage(fragename);
        model.addAttribute("test", wochenTest);
        model.addAttribute("frage", frage);
        return "wochentest";
    }

    @PostMapping("ExamByte/{testname}/{fragename}")
    public String antwortSpeichern(Model model,
                                   @PathVariable String testname,
                                   @PathVariable String fragename) {
        WochenTest wochenTest = wochenTestService.getWochenTest(testname);
        Frage frage = wochenTest.getFrage(fragename);
        model.addAttribute("test", wochenTest);
        model.addAttribute("frage", frage);
        //not yet implemented, duh
        return "wochentest";
    }
}
