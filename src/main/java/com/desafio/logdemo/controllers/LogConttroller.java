package com.desafio.logdemo.controllers;

import java.util.List;
import java.util.Optional;

import com.desafio.logdemo.model.Log;
import com.desafio.logdemo.service.LogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class LogConttroller {

    @Autowired
    private LogService logService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<Log>> listEntity() {
        return ResponseEntity.ok(logService.list());
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Optional<Log>> getEntity(@PathVariable(name = "id", required = true) Long id) {
        return ResponseEntity.ok(logService.get(id));
    }

    @PostMapping(value = "/add")
    public void addEntity(@RequestBody Log entity) {
        logService.add(entity);
    }

    @PutMapping(value = "/edit")
    public void editEntity(@RequestBody Log entity) {
        logService.edit(entity);
    }

    // no need delete map for now
    
}