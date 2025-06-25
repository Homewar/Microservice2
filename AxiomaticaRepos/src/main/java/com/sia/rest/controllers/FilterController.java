package com.sia.rest.controllers;

import com.sia.rest.entity.Person;
import com.sia.rest.entity.Document;
import com.sia.rest.repository.PersonRepository;
import com.sia.rest.repository.DocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
@Slf4j
public class FilterController {

    private final PersonRepository personRepo;
    private final DocumentRepository documentRepo;

    public FilterController(PersonRepository personRepo,
                            DocumentRepository documentRepo) {
        this.personRepo = personRepo;
        this.documentRepo = documentRepo;
    }

    @GetMapping("/persons")
    public ResponseEntity<Page<Person>> filterPersons(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String patronymic,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate birthDateFrom,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate birthDateTo,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime createdFrom,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime createdTo,
            Pageable pageable
    ) {
        Specification<Person> spec = null;

        if (name != null) {
            Specification<Person> s = (root, q, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
            spec = (spec == null ? s : spec.and(s));
        }
        if (surname != null) {
            Specification<Person> s = (root, q, cb) ->
                    cb.like(cb.lower(root.get("surname")), "%" + surname.toLowerCase() + "%");
            spec = (spec == null ? s : spec.and(s));
        }
        if (patronymic != null) {
            Specification<Person> s = (root, q, cb) ->
                    cb.like(cb.lower(root.get("patronymic")), "%" + patronymic.toLowerCase() + "%");
            spec = (spec == null ? s : spec.and(s));
        }
        if (birthDateFrom != null && birthDateTo != null) {
            Specification<Person> s = (root, q, cb) ->
                    cb.between(root.get("birthDate"), birthDateFrom, birthDateTo);
            spec = (spec == null ? s : spec.and(s));
        }
        if (gender != null) {
            Specification<Person> s = (root, q, cb) ->
                    cb.equal(root.get("gender"), gender);
            spec = (spec == null ? s : spec.and(s));
        }
        if (createdFrom != null && createdTo != null) {
            Specification<Person> s = (root, q, cb) ->
                    cb.between(root.get("createdAt"), createdFrom, createdTo);
            spec = (spec == null ? s : spec.and(s));
        }

        Page<Person> page = personRepo.findAll(spec, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/documents")
    public ResponseEntity<Page<Document>> filterDocuments(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String series,
            @RequestParam(required = false) String number,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate issueDateFrom,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate issueDateTo,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime createdFrom,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime createdTo,
            Pageable pageable
    ) {
        Specification<Document> spec = null;

        if (type != null) {
            Specification<Document> s = (root, q, cb) ->
                    cb.equal(root.get("type"), type);
            spec = (spec == null ? s : spec.and(s));
        }
        if (series != null) {
            Specification<Document> s = (root, q, cb) ->
                    cb.equal(root.get("series"), series);
            spec = (spec == null ? s : spec.and(s));
        }
        if (number != null) {
            Specification<Document> s = (root, q, cb) ->
                    cb.equal(root.get("number"), number);
            spec = (spec == null ? s : spec.and(s));
        }
        if (issueDateFrom != null && issueDateTo != null) {
            Specification<Document> s = (root, q, cb) ->
                    cb.between(root.get("issueDate"), issueDateFrom, issueDateTo);
            spec = (spec == null ? s : spec.and(s));
        }
        if (createdFrom != null && createdTo != null) {
            Specification<Document> s = (root, q, cb) ->
                    cb.between(root.get("createdAt"), createdFrom, createdTo);
            spec = (spec == null ? s : spec.and(s));
        }

        Page<Document> page = documentRepo.findAll(spec, pageable);
        return ResponseEntity.ok(page);
    }
}
