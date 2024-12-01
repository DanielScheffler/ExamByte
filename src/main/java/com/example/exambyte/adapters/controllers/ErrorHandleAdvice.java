package com.example.exambyte.adapters.controllers;

import com.example.exambyte.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandleAdvice {

    @ExceptionHandler(FrageNichtGefundenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleFrageNichtGefundenException(FrageNichtGefundenException e, Model model) {
        model.addAttribute("fehlermeldung", e.getMessage());
        e.printStackTrace();
        return "error";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TestNichtGefundenException.class)
    public String handleTestNichtGefundenException(TestNichtGefundenException e, Model model) {
        model.addAttribute("fehlermeldung", e.getMessage());
        e.printStackTrace();
        return "error";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ZeitraumUngueltigException.class)
    public String handleZeitraumUngueltigException(ZeitraumUngueltigException e, Model model) {
        model.addAttribute("fehlermeldung", e.getMessage());
        e.printStackTrace();
        return "error";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TestnameExistiertBereitsException.class)
    public String handleTestnameExistiertBereitsException(TestnameExistiertBereitsException e, Model model) {
        model.addAttribute("fehlermeldung", e.getMessage());
        return "error";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FragennameExistiertBereitsException.class)
    public String handleFragennameExistiertBereitsException(FragennameExistiertBereitsException e, Model model) {
        model.addAttribute("fehlermeldung", e.getMessage());
        e.printStackTrace();
        return "error";
    }

}
