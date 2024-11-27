package com.example.exambyte.controllers;

import com.example.exambyte.builder.FrageBuilder;
import com.example.exambyte.data.FRAGENTYP;
import com.example.exambyte.data.Frage;
import com.example.exambyte.data.FrageForm;
import com.example.exambyte.service.WochenTestService;
import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class fragenerstellungController {

    private final WochenTestService wochenTestService;

    public fragenerstellungController(WochenTestService wochenTestService) {
        this.wochenTestService = wochenTestService;
    }

    //Controller für die Erstellung von Fragen für Tests
    @GetMapping("/ExamByte/{testname}/fragenerstellung")
    @Secured("ROLE_ORGANISATOR")
    public String fragenerstellung(FrageForm frageForm, @PathVariable String testname, Model model){
        var maybeWochenTest = wochenTestService.getWochenTests().stream()
                .filter(test -> test.getName().equals(testname))
                .findAny();
        if(maybeWochenTest.isPresent()){
            model.addAttribute("test", maybeWochenTest.get());
        }
        return "fragenerstellung";
    }

    @PostMapping("/ExamByte/{testname}/fragenerstellung")
    @Secured("ROLE_ORGANISATOR")
    public String postFragenerstellung(@Valid FrageForm frageForm,
                                       BindingResult bindingResult,
                                       @PathVariable String testname,
                                       Model model){
        var maybeWochenTest = wochenTestService.getWochenTests().stream()
                .filter(test -> test.getName().equals(testname))
                .findAny();
        if(maybeWochenTest.isPresent()){
            model.addAttribute("test", maybeWochenTest.get());
        }

        if (bindingResult.hasErrors()){
            return "fragenerstellung";
        }
        FrageBuilder neueBuildFrage = new FrageBuilder()
                .addFragetyp(frageForm.fragentyp())
                .addName(frageForm.name())
                .addFragestellung(frageForm.fragestellung())
                .addMaxPunktzahl(frageForm.maxPunktzahl());
        if(frageForm.fragentyp().equals(FRAGENTYP.FRAGENTYP_MULTIPLECHOICE)){
            neueBuildFrage.addAntwortmoeglichkeiten(frageForm.antwortMoeglichkeiten());
        }
        Frage neueFrage = neueBuildFrage.build();
        if (maybeWochenTest.isPresent()){
            maybeWochenTest.get().addFrage(neueFrage);
        }
        return "redirect:/ExamByte/{testname}/"+ frageForm.name();
    }
}
