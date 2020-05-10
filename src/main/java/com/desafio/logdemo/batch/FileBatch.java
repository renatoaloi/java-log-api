package com.desafio.logdemo.batch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.desafio.logdemo.model.File;
import com.desafio.logdemo.model.Log;
import com.desafio.logdemo.repository.FileRepository;
import com.desafio.logdemo.repository.LogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class FileBatch {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private LogRepository logRepository;
    
    @Value("${logdemo.field.separator}")
    private String separator;

    //@Scheduled(fixedRate = 60000 * 5L) // every 5 min
    @Scheduled(fixedRate = 60000 * 1L) // every 1 min
    private void jobBatchFile() {
        List<File> files = fileRepository.findByFinishedProcessing(false);

        System.out.println("Batch process running...");

        for (File file : files) {
            // file is ok, load the data
            String filePath = file.getFileName();

            BufferedReader reader;
            try {

                // creating line-by-line reader
                reader = new BufferedReader(new FileReader(filePath));
                String line = reader.readLine();

                // Batch the logs for inserting into database
                List<Log> logs = new ArrayList<Log>();

                // Read the file
                while (line != null) {
                    String[] fields = line.split(separator);
                    LocalDate d = LocalDate.parse(fields[0].substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                    // new log
                    Log log = new Log();
                    log.setEntryDate(d);
                    log.setIpAddress(fields[1]);
                    log.setHttpMethod(fields[2]);
                    log.setReturnCode(Integer.parseInt(fields[3]));
                    log.setBrowserDetail(fields[4]);

                    // Add it to memory list
                    logs.add(log);
                    
                    // read next line
                    line = reader.readLine();
                }
                // closing the reader
                reader.close();

                // save all logs in batch mode
                logRepository.saveAll(logs);

            } catch (Exception e) {
                //e.printStackTrace();
                file.setProcessMsg(e.getMessage());
            }
            finally {
                // updating process done status
                file.setFinishedProcessing(true);
                fileRepository.saveAndFlush(file);
            }

        } 

        System.out.println("Batch process finished!");
        
    }
    
}