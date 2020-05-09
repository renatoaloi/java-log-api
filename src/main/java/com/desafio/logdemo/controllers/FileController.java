package com.desafio.logdemo.controllers;

import java.util.List;
import java.util.Optional;

import com.desafio.logdemo.model.File;
import com.desafio.logdemo.model.Log;
import com.desafio.logdemo.service.FileService;
import com.desafio.logdemo.service.LogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<File>> listEntity() {
        return ResponseEntity.ok(fileService.list());
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Optional<File>> getEntity(@PathVariable(name = "id", required = true) final Long id) {
        return ResponseEntity.ok(fileService.get(id));
    }

    @PostMapping(value = "/add")
    public void addEntity(@RequestBody final File entity) {
        fileService.add(entity);
    }

    @PutMapping(value = "/edit")
    public void editEntity(@RequestBody final File entity) {
        fileService.edit(entity);
    }

    @PostMapping(value = "/upload")
    public void uploadFile(@RequestParam final MultipartFile mfile) {
        //final Long savedFileId = fileService.uploadFile(mfile);
        // load the data async
        //logService.handleBatchFile(savedFileId);
    }
    
}