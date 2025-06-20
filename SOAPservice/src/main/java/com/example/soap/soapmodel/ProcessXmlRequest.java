package com.example.soap.soapmodel;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(namespace = "http://soap.sia.com/", name = "processXmlRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class ProcessXmlRequest {
    @XmlElement(name = "xml", namespace = "http://soap.sia.com/")
    private String xml;
}
