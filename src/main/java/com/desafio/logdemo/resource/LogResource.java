package com.desafio.logdemo.resource;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class LogResource {

    @Getter
    @Setter
    @JsonProperty(value="id")
    private Long id;

    @Getter
    @Setter
    @JsonProperty(value="entry_date")
    private LocalDate entryDate;

    @Getter
    @Setter
    @JsonProperty(value="ip_address")
    private String ipAddress;

    @Getter
    @Setter
    @JsonProperty(value="http_method")
    private String httpMethod;

    @Getter
    @Setter
    @JsonProperty(value="return_code")
    private int returnCode;

    @Getter
    @Setter
    @JsonProperty(value="browser_detail")
    private String browserDetail;

}