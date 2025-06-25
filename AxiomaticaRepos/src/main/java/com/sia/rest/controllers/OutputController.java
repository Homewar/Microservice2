/*
package com.sia.rest.controllers;

import com.sia.rest.entity.Document;
import com.sia.rest.entity.Person;
import com.sia.rest.repository.DocumentRepository;
import com.sia.rest.repository.PersonRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api")
public class OutputController {
    private final PersonRepository personRepository;
    private final DocumentRepository documentRepository;

    public OutputController(PersonRepository personRepository, DocumentRepository documentRepository) {
        this.personRepository = personRepository;
        this.documentRepository = documentRepository;
    }

    */
/**
     * GET /api/persons?page=0&size=10&sort=surname,asc
     *//*


    @GetMapping("/persons")
    public ResponseEntity<Page<Person>> getPersons(Pageable pageable) {
        Page<Person> page = personRepository.findAll(pageable);
        return ResponseEntity.ok(page);
    }


    */
/**
     * GET /api/documents?page=0&size=10&sort=issueDate,desc
     *//*


    @GetMapping("/documents")
    public ResponseEntity<Page<Document>> getDocument(Pageable pageable) {
        Page<Document> page = documentRepository.findAll(pageable);
        return ResponseEntity.ok(page);
    }
}
*/
