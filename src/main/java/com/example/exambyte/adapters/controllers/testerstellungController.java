package com.example.exambyte.adapters.controllers;

import com.example.exambyte.domain.service.builder.WochenTestBuilder;
import com.example.exambyte.domain.model.Frage;
import com.example.exambyte.domain.model.STATUS;
import com.example.exambyte.domain.model.TestForm;
import com.example.exambyte.domain.model.WochenTest;
import com.example.exambyte.application.service.WochenTestService;
import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class testerstellungController {

    private final WochenTestService wochenTestService;

    public testerstellungController(WochenTestService wochenTestService) {
        this.wochenTestService = wochenTestService;
    }

    //Controller für die Erstellung von Tests
    @GetMapping("/ExamByte/testerstellung")
    @Secured("ROLE_ORGANISATOR")
    public String testerstellung(TestForm testForm){
        return "testerstellung";
    }

    @PostMapping("/ExamByte/testerstellung")
    @Secured("ROLE_ORGANISATOR")
    public String testerstellung(@Valid TestForm testForm, BindingResult bindingResult, Frage frage){
        if(bindingResult.hasErrors()){
            return "testerstellung";
        }
        WochenTest neuerTest = new WochenTestBuilder()
                .addName(testForm.name())
                .addStartTime(testForm.startTime())
                .addEndTime(testForm.endTime())
                .addStatus(STATUS.STATUS_AUSSTEHEND)
                .build();
        wochenTestService.addWochenTest(neuerTest);
        return "redirect:/ExamByte";
    }

}
