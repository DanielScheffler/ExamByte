package com.example.exambyte.controllers;
import com.example.exambyte.builder.FrageBuilder;
import com.example.exambyte.builder.TestBuilder;
import com.example.exambyte.data.*;
import com.example.exambyte.service.WochenTestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebController {

    private final WochenTestService wochenTestService;

    public WebController(WochenTestService wochenTestService) {
        this.wochenTestService = wochenTestService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/ExamByte";
    }

    //Controller zur Startseite
    @GetMapping("/ExamByte")
    public String wochenuebersicht(Model model){
        model.addAttribute("tests", wochenTestService.getWochenTests());
        return "wochenuebersicht";
    }

    //Controller für die Erstellung von Tests
    @GetMapping("/ExamByte/testerstellung")
    public String testerstellung(TestForm testForm){
        return "testerstellung";
    }

    @PostMapping("/ExamByte/testerstellung")
    public String testerstellung(@ModelAttribute TestForm testForm, Frage frage){

        Frage neueFrage = new FrageBuilder()
                .addName(frage.name())
                .addFragestellung(frage.fragestellung())
                .addMaxPunktzahl(frage.maxPunktzahl())
                .addFragetyp(frage.fragentyp())
                .build();

        WochenTest neuerTest = new TestBuilder()
                .addName(testForm.name())
                .addStartTime(testForm.startDate())
                .addEndTime(testForm.endDate())
                .addFrage(neueFrage)
                .addStatus(STATUS.STATUS_AUSSTEHEND)
                .build();
        wochenTestService.addWochenTest(neuerTest);
        return "redirect:/";
    }

    //Controller für die Erstellung von Fragen für Tests
    @GetMapping("/ExamByte/testerstellung/fragenerstellung")
    public String fragenerstellung(FrageForm frageForm){
        return "fragenerstellung";
    }

    @PostMapping("/ExamByte/testerstellung/fragenerstellung/speichern")
    public String postFragenerstellung(FrageForm frageForm, RedirectAttributes redirectAttributes){
        Frage neueFrage = new FrageBuilder()
                .addFragetyp(frageForm.fragetyp())
                .addName(frageForm.name())
                .addFragestellung(frageForm.fragestellung())
                .addMaxPunktzahl(frageForm.maxPunktzahl())
                .addAntwortmoeglichkeiten(frageForm.antwortMoeglichkeiten())
                .build();
        redirectAttributes.addFlashAttribute("frage", neueFrage);
        return "redirect:/testerstellung";
    }

    //Controller für Tests
    @GetMapping("/ExamByte/{wochentestname}/{fragename}")
    public String fragen(Model model,
                         @PathVariable String wochentestname,
                         @PathVariable String fragename){
        var maybewWochenTest = wochenTestService.getWochenTests().stream()
                .filter(w -> w.getName().equals(wochentestname))
                .findAny();
        if(maybewWochenTest.isPresent()){
            model.addAttribute("test", maybewWochenTest.get());
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
    @GetMapping("ExamByte/Woche1/frage1_korrektur")
    public String korrekturen(){
        return "korrekturen";
    }

}