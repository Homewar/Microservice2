
package com.example.soap.soapmodel;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;

import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _ProcessXmlResponse_QNAME = new QName("http://soap.sia.com/", "processXmlResponse");

    public ObjectFactory() {
    }

    public ProcessXmlRequest createProcessXmlRequest() {
        return new ProcessXmlRequest();
    }

    public ProcessXmlResponse createProcessXmlResponse() {
        return new ProcessXmlResponse();
    }

    @XmlElementDecl(namespace = "http://soap.sia.com/", name = "processXmlResponse")
    public JAXBElement<ProcessXmlResponse> createProcessXmlResponse(ProcessXmlResponse value) {
        return new JAXBElement<>(_ProcessXmlResponse_QNAME, ProcessXmlResponse.class, null, value);
    }
}
