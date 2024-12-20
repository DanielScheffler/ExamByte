package com.example.exambyte.adapters.repositories;

import com.example.exambyte.domain.model.WochenTest;
import com.example.exambyte.application.service.WochenTestRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WochenTestRepositoryImpl implements WochenTestRepository {
    private List<WochenTest> wochenTests = new ArrayList<>();

    public List<WochenTest> findAll() {
        return wochenTests;
    }

    public void save(WochenTest wochenTest) {
        for (int i = 0; i < wochenTests.size(); i++) {
            if(wochenTests.get(i).getName().equals(wochenTest.getName())) {
                wochenTests.set(i, wochenTest);
                return;
            }
        }
        wochenTests.add(wochenTest);
    }

    public void remove(WochenTest wochenTest) {
        wochenTests.remove(wochenTest);
    }
}
