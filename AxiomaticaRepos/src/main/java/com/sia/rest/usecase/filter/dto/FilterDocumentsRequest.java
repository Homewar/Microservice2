package com.sia.rest.usecase.filter.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class FilterDocumentsRequest {
    private String type;
    private String series;
    private String number;
    private LocalDate issueDateFrom;
    private LocalDate issueDateTo;
    private LocalDateTime createdFrom;
    private LocalDateTime createdTo;
}