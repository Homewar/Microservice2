package com.sia.rest.usecase.filter;

import com.sia.rest.usecase.filter.dto.FilterPersonsRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.sia.rest.entity.Person;

public interface FilterPersonsUseCase {
    Page<Person> execute(FilterPersonsRequest request, Pageable pageable);
}