package com.example.exambyte.data;

import java.time.LocalDateTime;
import java.util.List;

public class WochenTest{
    List<Frage> frageList;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String name;

    public WochenTest(List<Frage> frageList, LocalDateTime startTime, LocalDateTime endTime,String name) {
        this.frageList = frageList;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
    }

    public void addFrage(Frage frage){
        frageList.add(frage);
    }

    public Frage getFrage(int index){
        return frageList.get(index);
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public List<Frage> getFrageList() {
        return frageList;
    }
}
