package com.desafio.logdemo.repository;

import com.desafio.logdemo.model.datasource.Log;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {

}