package com.example.exambyte.domain.service.builder;

import com.example.exambyte.domain.model.Frage;
import com.example.exambyte.domain.model.STATUS;
import com.example.exambyte.domain.model.WochenTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WochenTestBuilder {
    List<Frage> fragen;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String name;
    STATUS status;

    public WochenTestBuilder(){
        startTime = LocalDateTime.now();
        endTime = LocalDateTime.now().plusDays(1);
        name = "dummy";
        fragen = new ArrayList<>();
        status=STATUS.STATUS_AUSSTEHEND;
    }

    public WochenTestBuilder addEndTime(LocalDateTime endTime){
        this.endTime = endTime;
        return this;
    }

    public WochenTestBuilder addStartTime(LocalDateTime startTime){
        this.startTime = startTime;
        return this;
    }

    public WochenTestBuilder addFrage(Frage frage){
        fragen.add(frage);
        return this;
    }

    public WochenTestBuilder addName(String name){
        this.name = name;
        return this;
    }

    public WochenTestBuilder addStatus(STATUS status){
        this.status = status;
        return this;
    }


    public WochenTest build() {
        return new WochenTest(fragen,startTime,endTime,name,status);
    }
}
