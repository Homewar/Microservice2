package com.sia.rest.usecase.filter.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class FilterPersonsRequest {
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthDateFrom;
    private LocalDate birthDateTo;
    private String gender;
    private LocalDateTime createdFrom;
    private LocalDateTime createdTo;
}
