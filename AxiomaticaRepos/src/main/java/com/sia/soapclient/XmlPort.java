package com.sia.soapclient;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;

@WebService(targetNamespace = "http://soap.sia.com/", name = "XmlPort")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface XmlPort {

    @WebMethod
    @WebResult(name = "processXmlResponse", targetNamespace = "http://soap.sia.com/", partName = "processXmlResponse")
    public ProcessXmlResponse processXml(

        @WebParam(partName = "processXmlRequest", name = "processXmlRequest", targetNamespace = "http://soap.sia.com/")
        ProcessXmlRequest processXmlRequest
    );
}
