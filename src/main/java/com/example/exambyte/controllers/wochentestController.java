package com.example.exambyte.controllers;

import com.example.exambyte.service.WochenTestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class wochentestController {

    private final WochenTestService wochenTestService;

    public wochentestController(WochenTestService wochenTestService) {
        this.wochenTestService = wochenTestService;
    }

    //Handler für einen Test ohne Fragen
    @GetMapping("/ExamByte/{testname}")
    public String fragenLeer(Model model, @PathVariable String testname){
        var maybeWochenTest = wochenTestService.getWochenTests().stream()
                .filter(w -> w.getName().equals(testname))
                .findAny();
        if(maybeWochenTest.isPresent()){
            model.addAttribute("test", maybeWochenTest.get());
        } else {
            model.addAttribute("error", "Keinen Wochentest mit diesem Namen gefunden.");
        }
        return "wochentest";
    }

    //Controller für Tests
    @GetMapping("/ExamByte/{testname}/{fragename}")
    public String fragen(Model model,
                         @PathVariable String testname,
                         @PathVariable String fragename){
        var maybeWochenTest = wochenTestService.getWochenTests().stream()
                .filter(w -> w.getName().equals(testname))
                .findAny();
        if(maybeWochenTest.isPresent()){
            model.addAttribute("test", maybeWochenTest.get());
            var maybeFrage = maybeWochenTest.get().getFragen().stream()
                    .filter(f->f.name().equals(fragename))
                    .findAny();
            if(maybeFrage.isPresent()){
                model.addAttribute("frage", maybeFrage.get());
            }
        } else {
            model.addAttribute("error", "Keinen Wochentest mit diesem Namen gefunden.");
        }
        return "wochentest";
    }
}
