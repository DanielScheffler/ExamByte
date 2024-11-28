package com.example.exambyte.controllers;

import com.example.exambyte.service.WochenTestService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
    @Secured("ROLE_STUDENT")
    public String wochenuebersicht(Model model, @AuthenticationPrincipal OAuth2User principal){
        model.addAttribute("authorities", principal.getAuthorities().toString());
        model.addAttribute("tests", wochenTestService.getWochenTests());
        return "index";
    }
}
