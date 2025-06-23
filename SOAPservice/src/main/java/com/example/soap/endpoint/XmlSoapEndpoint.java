package com.example.soap.endpoint;

import com.example.soap.model.Person;
import com.example.soap.soapmodel.ObjectFactory;
import com.example.soap.soapmodel.ProcessXmlRequest;
import com.example.soap.soapmodel.ProcessXmlResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

@Endpoint
@Slf4j
public class XmlSoapEndpoint {
    private static final String NAMESPACE_URI = "http://soap.sia.com/";
    private final JAXBContext personContext;
    private final ObjectFactory soapFactory;

    public XmlSoapEndpoint() throws JAXBException {
        this.personContext = JAXBContext.newInstance(Person.class);
        this.soapFactory = new ObjectFactory();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "processXmlRequest")
    @ResponsePayload
    public JAXBElement<ProcessXmlResponse> processXml(
            @RequestPayload JAXBElement<ProcessXmlRequest> requestElement
    ) throws Exception {
        ProcessXmlRequest request = requestElement.getValue();
        String inputXml = request.getXml();
        log.info("=== SOAP-запрос получен ===\n{}", inputXml);

        // 1) XML → объект Person
        Unmarshaller unmarshaller = personContext.createUnmarshaller();
        Person person = (Person) unmarshaller.unmarshal(new StringReader(inputXml));

        // 2) Объект → XML (raw)
        Marshaller marshaller = personContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter xmlWriter = new StringWriter();
        marshaller.marshal(person, xmlWriter);

        // 3) XSLT-трансформация
        String transformedXml = transformWithXslt(xmlWriter.toString());
        log.info("=== SOAP-ответ сформирован ===\n{}", transformedXml);

        // 4) Формируем Element с CDATA внутри
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        // Создаем элемент <result> в нужном пространстве имен
        Element resultElement = doc.createElementNS("http://soap.sia.com/", "ns2:result");
        String cleanedXml = transformedXml.replace("\r", "");

        // Вставляем CDATA с вашим преобразованным XML
        CDATASection cdata = doc.createCDATASection(cleanedXml);
        resultElement.appendChild(cdata);

        // Устанавливаем элемент в response
        ProcessXmlResponse response = new ProcessXmlResponse();
        response.setResult(resultElement);

        return soapFactory.createProcessXmlResponse(response);
    }

    private String transformWithXslt(String inputXml) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            ClassPathResource xsltRes = new ClassPathResource("transform.xslt");
            if (!xsltRes.exists()) {
                log.warn("XSLT not found; возвращаю исходный XML");
                return inputXml;
            }
            Source xslt = new StreamSource(xsltRes.getInputStream());
            Transformer transformer = factory.newTransformer(xslt);
            Source text = new StreamSource(new StringReader(inputXml));
            StringWriter out = new StringWriter();
            transformer.transform(text, new StreamResult(out));
            return out.toString();
        } catch (Exception e) {
            log.error("Ошибка XSLT-преобразования; возвращаю исходный XML", e);
            return inputXml;
        }
    }
}
