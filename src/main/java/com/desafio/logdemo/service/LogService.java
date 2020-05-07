package com.desafio.logdemo.service;

import java.util.List;
import java.util.Optional;

import com.desafio.logdemo.model.Log;
import com.desafio.logdemo.repository.LogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public Optional<Log> get(Long id) {
        return logRepository.findById(id);
    }

    public void add(Log entity) {
        logRepository.saveAndFlush(entity);
    }

    public List<Log> list() {
        return logRepository.findAll();
    }

    public void edit(Log entity) {
        this.add(entity);
    }

}