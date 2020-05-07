package com.desafio.logdemo.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.desafio.logdemo.model.File;
import com.desafio.logdemo.repository.FileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    @Value("${logdemo.storage.path}")
    private String storagePath;

    public Long uploadFile(MultipartFile mfile) {
        
        // creating unique filename
        String originalName = mfile.getOriginalFilename();
        String filePathName = originalName.substring(0, originalName.lastIndexOf(".")).replace(".", "");
        String filePathExt = originalName.substring(originalName.lastIndexOf(".")).replace(".", "");
        String formatedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HmsA"));
        String filePath = filePathName + "_" + formatedDate + "." + filePathExt;
        //System.out.println(filePath);

        // Getting path from file path
        Path dir = Paths.get(storagePath);
        Path dirFile = dir.resolve(filePath);

        // fill up file entity to save it
        File entity = new File();
        entity.setFileName(dirFile.toString()); 
        entity.setDateCreated(LocalDateTime.now());
        entity.setFinishedProcessing(false);
        
        // now try to save the file
        try {
            Files.createDirectories(dirFile);
            mfile.transferTo(dirFile.toFile());
        } catch (Exception e) {
            entity.setErrorMsg(e.getMessage());
        }

        // save, flush and return the entity id
        fileRepository.saveAndFlush(entity);
        return entity.getId();
    }
}