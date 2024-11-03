package com.example.exambyte.service;

import com.example.exambyte.data.WochenTest;
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
}
