package com.desafio.logdemo.repository;

import java.util.List;

import com.desafio.logdemo.model.File;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FileRepository extends JpaRepository<File, Long> {

    @Query("FROM File WHERE finishedProcessing = ?1")
    List<File> findByFinishedProcessing(boolean isFinished);
    
}

