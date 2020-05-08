package com.desafio.logdemo.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.desafio.logdemo.model.File;
import com.desafio.logdemo.model.Log;
import com.desafio.logdemo.repository.FileRepository;
import com.desafio.logdemo.repository.LogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private FileRepository fileRepository;

    @Value("${logdemo.field.separator}")
    private String separator;

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
            File logFile = file.get();

            // file is ok, load the data
            String filePath = logFile.getFileName();
            System.out.println(filePath);

            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(filePath));
                String line = reader.readLine();
                while (line != null) {
                    System.out.println(line);
                    // 
                    String[] fields = line.split("|");
                    System.out.println(line);
                    for (int i = 0; i < fields.length; i++) {
                        System.out.println(fields[0]);
                        System.out.println(fields[1]);
                        System.out.println(fields[2]);
                        System.out.println(fields[3]);
                        System.out.println(fields[4]);

                        break;

                        /*Log log = new Log();
                        log.setEntryDate(LocalDate.parse(fields[0], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                        log.setIpAddress(fields[1]);
                        log.setHttpMethod(fields[2]);
                        log.setReturnCode(Integer.parseInt(fields[3]));
                        log.setBrowserDetail(fields[4]);
                        logRepository.saveAndFlush(log);*/
                    }
                    // read next line
                    line = reader.readLine();
                }
                reader.close();

                // updating process done status
                logFile.setFinishedProcessing(true);
                

            } catch (IOException e) {
                e.printStackTrace();
                logFile.setProcessMsg(e.getMessage());
            }

            fileRepository.saveAndFlush(logFile);
        }
    }

}