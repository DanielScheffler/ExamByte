package com.example.exambyte.data;

import com.example.exambyte.exceptions.FrageNichtGefundenException;
import com.example.exambyte.exceptions.FragennameExistiertBereitsException;
import com.example.exambyte.exceptions.ZeitraumUngueltigException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        if(endTime.isBefore(startTime) || endTime.isEqual(startTime) || startTime.isBefore(LocalDateTime.now())){
            throw new ZeitraumUngueltigException("Der gewählte Zeitraum ist nicht gültig!");
        }
    }

    public String getName() { return name; }

    public void addFrage(Frage frage){
        if(frageList.stream().map(Frage::name).noneMatch(name -> name.equals(frage.name()))){
            frageList.add(frage);
        } else {
            throw new FragennameExistiertBereitsException("Frage " + frage.name() + " existiert bereits");
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
        var maybeFrage = frageList.stream().filter(frage -> frage.name().equals(name)).findFirst();

        if(maybeFrage.isPresent()){
            return maybeFrage.get();
        } else {
            throw new FrageNichtGefundenException("Diese Frage konnte nicht gefunden werden!");
        }
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
