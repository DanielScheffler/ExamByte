package com.example.exambyte.service;

import com.example.exambyte.data.WochenTest;
import com.example.exambyte.exceptions.TestNichtGefundenException;
import com.example.exambyte.repositories.WochenTestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WochenTestService {

    WochenTestRepository wochenTestRepository;

    WochenTestService(WochenTestRepository wochenTestRepository) {
        this.wochenTestRepository = wochenTestRepository;
    }

    public void addWochenTest(WochenTest wochenTest) {
        wochenTestRepository.save(wochenTest);
    }

    public List<WochenTest> getWochenTests() {
        return wochenTestRepository.findAll();
    }

    public void removeWochenTest(WochenTest wochenTest) {
        wochenTestRepository.remove(wochenTest);
    }

    public WochenTest getWochenTest(String name) {
        var maybeWochenTest = wochenTestRepository.findAll().stream()
                .filter(w -> w.getName().equals(name))
                .findFirst();

        if(maybeWochenTest.isPresent()) {
            return maybeWochenTest.get();
        } else {
            throw new TestNichtGefundenException();
        }
    }
}
