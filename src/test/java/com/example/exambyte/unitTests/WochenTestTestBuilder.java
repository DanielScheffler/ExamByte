package com.example.exambyte.unitTests;

import com.example.exambyte.data.Frage;
import com.example.exambyte.data.STATUS;
import com.example.exambyte.data.WochenTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WochenTestTestBuilder {
    List<Frage> fragen;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String name;
    STATUS status;

    public WochenTestTestBuilder(){
        startTime = LocalDateTime.now();
        endTime = LocalDateTime.now().plusDays(1);
        name = "dummy";
        fragen = new ArrayList<>();
        status=STATUS.STATUS_AUSSTEHEND;
    }

    public WochenTestTestBuilder addEndTime(LocalDateTime endTime){
        this.endTime = endTime;
        return this;
    }

    public WochenTestTestBuilder addStartTime(LocalDateTime startTime){
        this.startTime = startTime;
        return this;
    }

    public WochenTestTestBuilder addFrage(Frage frage){
        fragen.add(frage);
        return this;
    }

    public WochenTestTestBuilder addName(String name){
        this.name = name;
        return this;
    }

    public WochenTestTestBuilder addStatus(STATUS status){
        this.status = status;
        return this;
    }


    public WochenTest build() {
        return new WochenTest(fragen,startTime,endTime,name,status);
    }
}
