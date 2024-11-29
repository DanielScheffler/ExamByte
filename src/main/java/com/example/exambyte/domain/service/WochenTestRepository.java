package com.example.exambyte.domain.service;

import com.example.exambyte.domain.model.WochenTest;

import java.util.List;

public interface WochenTestRepository {
    public List<WochenTest> findAll();
    public void save(WochenTest wochenTest);
    public void remove(WochenTest wochenTest);
}
