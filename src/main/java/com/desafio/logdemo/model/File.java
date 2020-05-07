package com.desafio.logdemo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "tfile")
public class File {

    @Id
    @Getter
    @Setter
    @GenericGenerator(name = "primaryIncrement", strategy = "increment")
    @GeneratedValue(generator = "primaryIncrement")
    private Long id;

    @Getter
    @Setter
    @Column(name = "file_name")
    private String fileName;

    @Getter
    @Setter
    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Getter
    @Setter
    @Column(name = "finished_processing")
    private Boolean finishedProcessing;

    @Getter
    @Setter
    @Column(name = "error_msg", length = 2000)
    private String errorMsg;
}