package com.sia.rest.controllers;

import com.sia.rest.entity.Person;
import com.sia.rest.entity.Document;
import com.sia.rest.usecase.filter.FilterDocumentsUseCase;
import com.sia.rest.usecase.filter.FilterPersonsUseCase;
import com.sia.rest.usecase.filter.dto.FilterDocumentsRequest;
import com.sia.rest.usecase.filter.dto.FilterPersonsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class FilterController {

    private final FilterPersonsUseCase personsUseCase;
    private final FilterDocumentsUseCase docsUseCase;

    public FilterController(FilterPersonsUseCase personsUseCase,
                            FilterDocumentsUseCase docsUseCase) {
        this.personsUseCase = personsUseCase;
        this.docsUseCase    = docsUseCase;
    }

    @GetMapping("/persons")
    public ResponseEntity<Page<Person>> filterPersons(
            FilterPersonsRequest request,
            Pageable pageable
    ) {
        Page<Person> page = personsUseCase.execute(request, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/documents")
    public ResponseEntity<Page<Document>> filterDocuments(
            FilterDocumentsRequest request,
            Pageable pageable
    ) {
        Page<Document> page = docsUseCase.execute(request, pageable);
        return ResponseEntity.ok(page);
    }
}
