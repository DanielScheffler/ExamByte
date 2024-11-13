package com.example.exambyte.controllers;

import com.example.exambyte.exceptions.FrageNichtGefundenException;
import com.example.exambyte.exceptions.TestNichtGefundenException;
import com.example.exambyte.exceptions.TestnameExistiertBereitsException;
import com.example.exambyte.exceptions.ZeitraumUngueltigException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandleAdvice {

    @ExceptionHandler(FrageNichtGefundenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleFrageNichtGefundenException(FrageNichtGefundenException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TestNichtGefundenException.class)
    public String handleTestNichtGefundenException(TestNichtGefundenException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ZeitraumUngueltigException.class)
    public String handleZeitraumUngueltigException(ZeitraumUngueltigException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TestnameExistiertBereitsException.class)
    public String handleTestnameExistiertBereitsException(TestnameExistiertBereitsException e) {
        return e.getMessage();
    }

}
