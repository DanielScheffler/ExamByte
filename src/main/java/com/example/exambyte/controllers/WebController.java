package com.example.exambyte.controllers;
import com.example.exambyte.builder.FrageBuilder;
import com.example.exambyte.builder.TestBuilder;
import com.example.exambyte.data.*;
import com.example.exambyte.service.WochenTestService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "index";
    }

    //Controller für die Erstellung von Tests
    @GetMapping("/ExamByte/testerstellung")
    public String testerstellung(TestForm testForm){
        return "testerstellung";
    }

    @PostMapping("/ExamByte/testerstellung")
    public String testerstellung(@Valid TestForm testForm, BindingResult bindingResult, Frage frage){
        if(bindingResult.hasErrors()){
            return "testerstellung";
        }
        WochenTest neuerTest = new TestBuilder()
                .addName(testForm.name())
                .addStartTime(testForm.startTime())
                .addEndTime(testForm.endTime())
                .addStatus(STATUS.STATUS_AUSSTEHEND)
                .build();
        wochenTestService.addWochenTest(neuerTest);
        return "redirect:/";
    }

    //Controller für die Erstellung von Fragen für Tests
    @GetMapping("/ExamByte/{testname}/fragenerstellung")
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
    public String postFragenerstellung(@Valid FrageForm frageForm, BindingResult bindingResult, @PathVariable String testname, Model model){
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

    //Controller für die Korrektur von Fragen
    @GetMapping("ExamByte/Woche1/frage1_korrektur")
    public String korrekturen(){
        return "korrekturen";
    }

}