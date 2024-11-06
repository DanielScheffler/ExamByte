package com.example.exambyte.data;

import com.example.exambyte.builder.FrageBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WochenTest{
    private List<Frage> frageList = new ArrayList<>();
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String name;
    private STATUS status;

    public WochenTest(List<Frage> frageList, LocalDateTime startTime, LocalDateTime endTime, String name, STATUS status) {
        this.frageList = frageList;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.status = status;
    }

    public String getName() { return name; }

    public void addFrage(Frage frage){
        if(getFrage(frage.name()) == null) {
            frageList.add(frage);
        }
    }

    public Frage getNextFrage(String name) {
        for (int i = 0; i < frageList.size(); i++) {
            if (frageList.get(i).name().equals(name)) {
                if (i + 1> frageList.size() - 1) {
                    return frageList.getFirst();
                } else {
                    return frageList.get(i + 1);
                }
            }
        }
        System.out.println("Keine Frage mit dem Namen gefunden");
        return null;
    }


    public Frage getPrevFrage(String name) {
        Frage previous = frageList.get(frageList.size()-1);
        for (Frage frage : frageList) {
            if (frage.name().equals(name)) {
                return previous;
            }
            previous = frage;
        }
        return null;
    }

    public Frage getFrage(String name){
        return frageList.stream().filter(e->e.name().equals(name)).findFirst().orElse(null);
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public List<Frage> getFragen(){
        return frageList;
    }

    public STATUS getStatus() {return status;}
}
