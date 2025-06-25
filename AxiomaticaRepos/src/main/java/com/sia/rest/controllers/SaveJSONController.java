package com.sia.rest.controllers;

import com.sia.rest.entity.Person;
import com.sia.rest.usecase.save.ProcessPersonUseCase;
import com.sia.rest.usecase.save.dto.ProcessPersonResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class SaveJSONController {

    private final ProcessPersonUseCase processPersonUseCase;

    public SaveJSONController(ProcessPersonUseCase processPersonUseCase) {
        this.processPersonUseCase = processPersonUseCase;
    }

    @PostMapping("/persons")
    public ResponseEntity<String> processPerson(@RequestBody @Valid Person person) {
        ProcessPersonResponse resp = processPersonUseCase.execute(person);
        return ResponseEntity.ok(resp.getRawXml());
    }
}