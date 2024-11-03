package com.example.exambyte.controllers;
import com.example.exambyte.builder.FrageBuilder;
import com.example.exambyte.data.*;
import com.example.exambyte.service.WochenTestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
public class WebController {

    private final WochenTestService wochenTestService;

    public WebController(WochenTestService wochenTestService) {
        this.wochenTestService = wochenTestService;
    }

    //Controller zur Startseite
    @GetMapping("/")
    public String wochenuebersicht(Model model){
        model.addAttribute("tests", wochenTestService.getWochenTests());
        return "wochenuebersicht";
    }

    //Controller für die Erstellung von Tests
    @GetMapping("/testerstellung")
    public String testerstellung(Model model){
        return "testerstellung";
    }

    //Controller für die Erstellung von Fragen für Tests
    @GetMapping("/testerstellung/fragenerstellung")
    public String fragenerstellung(FrageForm frageForm){
        return "fragenerstellung";
    }

    @PostMapping("/testerstellung/fragenerstellung/speichern")
    public String postFragenerstellung(FrageForm frageForm,
                                       Model model,
                                       @ModelAttribute ArrayList<Frage> fragen,
                                       RedirectAttributes redirectAttributes){
        Frage neueFrage = new FrageBuilder()
                .addFragetyp(frageForm.fragetyp())
                .addName(frageForm.name())
                .addFragestellung(frageForm.fragestellung())
                .addMaxPunktzahl(frageForm.maxPunktzahl())
                .addAntwortmöglichkeiten(frageForm.antwortmöglichkeiten())
                .build();
        fragen.add(neueFrage);
        redirectAttributes.addFlashAttribute("fragen", fragen);
        return "redirect:/testerstellung";
    }

    //Controller für Tests
    @GetMapping("/{wochentestname}/{fragename}")
    public String fragen(Model model,
                         @PathVariable String wochentestname,
                         @PathVariable String fragename){
        var maybewWochenTest = wochenTestService.getWochenTests().stream()
                .filter(w -> w.getName().equals(wochentestname))
                .findAny();
        if(maybewWochenTest.isPresent()){
            model.addAttribute("wochenTest", maybewWochenTest.get());
            var maybeFrage = maybewWochenTest.get().getFragen().stream()
                    .filter(f->f.name().equals(fragename))
                    .findAny();
            if(maybeFrage.isPresent()){
                model.addAttribute("frage", maybeFrage.get());
            }
        } else {
            model.addAttribute("error", "Keinen Wochentest mit diesem Namen gefunden.");
        }
        return "fragen";
    }

    //Controller für die Korrektur von Fragen
    @GetMapping("/Woche1/frage1_korrektur")
    public String korrekturen(){
        return "korrekturen";
    }

}