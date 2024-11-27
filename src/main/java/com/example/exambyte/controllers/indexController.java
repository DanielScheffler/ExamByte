package com.example.exambyte.controllers;

import com.example.exambyte.service.WochenTestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

    private final WochenTestService wochenTestService;

    public indexController(WochenTestService wochenTestService) {
        this.wochenTestService = wochenTestService;
    }

    @GetMapping("/")
    public String index() {
        return "startseite";
    }

    //Controller zur Startseite
    @GetMapping("/ExamByte")
    public String wochenuebersicht(Model model){
        model.addAttribute("tests", wochenTestService.getWochenTests());
        return "index";
    }
}
