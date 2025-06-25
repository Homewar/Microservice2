package com.sia.rest.usecase.impl.save;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.SoapFaultClientException;

import com.sia.rest.adapter.XmlConverter;
import com.sia.rest.entity.Person;
import com.sia.rest.entity.SoapResponseEntity;
import com.sia.rest.repository.PersonRepository;
import com.sia.rest.repository.SoapResponseRepository;
import com.sia.rest.usecase.save.ProcessPersonUseCase;
import com.sia.rest.usecase.save.dto.ProcessPersonResponse;
import com.sia.soapclient.ProcessXmlRequest;
import com.sia.soapclient.ProcessXmlResponse;

import java.time.LocalDateTime;

@Service
public class ProcessPersonUseCaseImpl implements ProcessPersonUseCase {

    private final PersonRepository personRepo;
    private final SoapResponseRepository soapRepo;
    private final WebServiceTemplate wsTemplate;

    public ProcessPersonUseCaseImpl(PersonRepository personRepo,
                                    SoapResponseRepository soapRepo,
                                    WebServiceTemplate wsTemplate) {
        this.personRepo = personRepo;
        this.soapRepo = soapRepo;
        this.wsTemplate = wsTemplate;
    }

    @Override
    @Transactional
    public ProcessPersonResponse execute(Person person) {
        Person saved = personRepo.save(person);

        String xml;
        try {
            xml = XmlConverter.converter(saved);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка конвертации объекта в XML", e);
        }

        ProcessXmlRequest req = new ProcessXmlRequest();
        req.setXml(xml);

        ProcessXmlResponse resp;
        try {
            resp = (ProcessXmlResponse) wsTemplate
                    .marshalSendAndReceive("http://localhost:8080/ws", req);
        } catch (SoapFaultClientException ex) {
            throw new RuntimeException("SOAP Fault: " + ex.getFaultStringOrReason(), ex);
        }

        String raw = resp.getResult();
        // Сохраняем ответ
        SoapResponseEntity entity = SoapResponseEntity.builder()
                .responseBody(raw)
                .receivedAt(LocalDateTime.now())
                .build();
        soapRepo.save(entity);

        // Формируем DTO ответа
        return ProcessPersonResponse.builder()
                .rawXml(raw)
                .receivedAt(entity.getReceivedAt())
                .build();
    }
}
