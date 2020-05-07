package com.desafio.logdemo.repository;

import com.desafio.logdemo.model.File;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
    
}