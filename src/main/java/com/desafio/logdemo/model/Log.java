package com.desafio.logdemo.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name="tlog")
public class Log implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7738229485538592985L;

    @Id
    @Getter 
    @Setter
    @GenericGenerator(name = "primaryIncrement", strategy = "increment")
    @GeneratedValue(generator = "primaryIncrement")
    private Long id;

    @Getter 
    @Setter 
    @Column(name="entry_date")
    private LocalDate entryDate;

    @Getter 
    @Setter
    @Column(name="ip_address")
    private String ipAddress;

    @Getter 
    @Setter
    @Column(name="http_method")
    private String httpMethod;

    @Getter 
    @Setter
    @Column(name="return_code")
    private int returnCode;

    @Getter 
    @Setter
    @Column(name="browser_detail")
    private String browserDetail;
    
}