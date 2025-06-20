package com.sia.rest.controllers;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ws")
public class SoapMockController {

    @PostMapping(consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
    public String mockSoapResponse(@RequestBody String body) {
        return """
            <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
              <SOAP-ENV:Header/>
              <SOAP-ENV:Body>
                <ns2:processXmlResponse xmlns:ns2="http://soap.sia.com/">
                  <ns2:result>
                    &lt;person name="Иван" surname="Иванов" patronymic="Иванович" birthDate="1990-01-01" gender="MAN"&gt;
                      &lt;document series="1234" number="567890" type="PASSPORT" issueDate="2010-01-01"/&gt;
                    &lt;/person&gt;
                  </ns2:result>
                </ns2:processXmlResponse>
              </SOAP-ENV:Body>
            </SOAP-ENV:Envelope>
            """;
    }
}