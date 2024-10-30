package com.example.exambyte.data;

import java.time.LocalDateTime;
import java.util.List;

public class WochenTest{
    private List<Frage> frageList;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String name;
    private STATUS status;

    public WochenTest(List<Frage> frageList, LocalDateTime startTime, LocalDateTime endTime,String name, STATUS status) {
        this.frageList = frageList;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.status = status;
    }

    public String getName() { return name; }

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

    public List<Frage> getFrageList() {return frageList;}

    public STATUS getStatus() {return status;}
}
