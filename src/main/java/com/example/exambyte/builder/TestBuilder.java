package com.example.exambyte.builder;

import com.example.exambyte.data.Frage;
import com.example.exambyte.data.STATUS;
import com.example.exambyte.data.WochenTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestBuilder {
    List<Frage> fragen;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String name;
    STATUS status;

    public TestBuilder(){
        startTime = LocalDateTime.now();
        endTime = LocalDateTime.now().plusDays(1);
        name = "dummy";
        fragen = new ArrayList<>();
        status=STATUS.STATUS_AUSSTEHEND;
    }

    public TestBuilder addEndTime(LocalDateTime endTime){
        this.endTime = endTime;
        return this;
    }

    public TestBuilder addStartTime(LocalDateTime startTime){
        this.startTime = startTime;
        return this;
    }

    public TestBuilder addFrage(Frage frage){
        fragen.add(frage);
        return this;
    }

    public TestBuilder addName(String name){
        this.name = name;
        return this;
    }

    public TestBuilder addStatus(STATUS status){
        this.status = status;
        return this;
    }


    public WochenTest build() {
        return new WochenTest(fragen,startTime,endTime,name,status);
    }
}
