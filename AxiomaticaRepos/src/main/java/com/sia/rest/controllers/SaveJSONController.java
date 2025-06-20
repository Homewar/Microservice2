package com.sia.rest.controllers;

import com.sia.rest.repository.SoapResponseRepository;
import com.sia.rest.adapter.XmlConverter;
import com.sia.rest.entity.Person;
import com.sia.rest.entity.SoapResponseEntity;
import com.sia.soapclient.ProcessXmlRequest;
import com.sia.soapclient.ProcessXmlResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.SoapFaultClientException;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
@Slf4j
public class SaveJSONController {

    private final WebServiceTemplate webServiceTemplate;
    private final SoapResponseRepository soapResponseRepository;

    public SaveJSONController(WebServiceTemplate webServiceTemplate,
                              SoapResponseRepository soapResponseRepository) {
        this.webServiceTemplate = webServiceTemplate;
        this.soapResponseRepository = soapResponseRepository;
    }

    @PostMapping("/persons")
    public ResponseEntity<String> processPerson(@RequestBody @Valid Person person) {
        try {
            log.info("Received person: {}", person);
            String xml = XmlConverter.converter(person);
            log.info("Generated XML:\n{}", xml);

            ProcessXmlRequest request = new ProcessXmlRequest();
            request.setXml(xml);

            ProcessXmlResponse response = (ProcessXmlResponse)
                    webServiceTemplate.marshalSendAndReceive(
                            "http://localhost:8080/ws", request,
                            new WebServiceMessageCallback() {
                                @Override
                                public void doWithMessage(WebServiceMessage message) {
                                }
                            });

            if (response == null) {
                log.error("Received null response from SOAP service");
                return ResponseEntity.status(500).body("Null response from SOAP service");
            }

            String rawxml = response.getResult();
            log.info("Raw XML: {}", rawxml);

            SoapResponseEntity entity = SoapResponseEntity.builder()
                    .responseBody(rawxml)
                    .receivedAt(LocalDateTime.now())
                    .build();

            soapResponseRepository.save(entity);

            return ResponseEntity.ok(rawxml);

        } catch (SoapFaultClientException soapEx) {
            log.error("SOAP Fault: {}", soapEx.getFaultStringOrReason(), soapEx);
            return ResponseEntity.status(500).body("SOAP Fault: " + soapEx.getFaultStringOrReason());
        } catch (Exception e) {
            log.error("Error processing SOAP request", e);
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
