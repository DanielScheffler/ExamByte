package com.example.exambyte.application.service;

import com.example.exambyte.domain.model.WochenTest;

import java.util.List;

public interface WochenTestRepository {
    List<WochenTest> findAll();
    void save(WochenTest wochenTest);
    void remove(WochenTest wochenTest);
}
