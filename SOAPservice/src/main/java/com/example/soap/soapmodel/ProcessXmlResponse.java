package com.example.soap.soapmodel;

import org.w3c.dom.Element;
import jakarta.xml.bind.annotation.XmlAnyElement;

public class ProcessXmlResponse {

    private Element result;

    @XmlAnyElement
    public Element getResult() {
        return result;
    }

    public void setResult(Element result) {
        this.result = result;
    }
}