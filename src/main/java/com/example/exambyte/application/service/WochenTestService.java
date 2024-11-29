package com.example.exambyte.application.service;

import com.example.exambyte.domain.model.WochenTest;
import com.example.exambyte.exceptions.TestNichtGefundenException;
import com.example.exambyte.exceptions.TestnameExistiertBereitsException;
import com.example.exambyte.repositories.WochenTestRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WochenTestService {

    WochenTestRepositoryImpl wochenTestRepository;

    WochenTestService(WochenTestRepositoryImpl wochenTestRepository) {
        this.wochenTestRepository = wochenTestRepository;
    }

    public void addWochenTest(WochenTest wochenTest) {
        boolean isPresent = wochenTestRepository.findAll().stream()
                .map(WochenTest::getName).anyMatch(t->t.equals(wochenTest.getName()));
        if(isPresent) {
            throw new TestnameExistiertBereitsException("Dieser Testname existiert bereits.");
        } else {
            wochenTestRepository.save(wochenTest);
        }
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
            throw new TestNichtGefundenException("Dieser Test konnte nicht gefunden werden.");
        }
    }
}
