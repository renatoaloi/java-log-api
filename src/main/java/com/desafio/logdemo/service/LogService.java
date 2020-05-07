package com.desafio.logdemo.service;

import java.util.List;
import java.util.Optional;

import com.desafio.logdemo.model.File;
import com.desafio.logdemo.model.Log;
import com.desafio.logdemo.repository.FileRepository;
import com.desafio.logdemo.repository.LogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private FileRepository fileRepository;

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

    @Async
    public void handleBatchFile(Long fileId) {
        Optional<File> file = fileRepository.findById(fileId);
        if (file.isPresent()) {
            // file is ok, load the data
            String filePath = file.get().getFileName();
            System.out.println(filePath);
        }
    }

}