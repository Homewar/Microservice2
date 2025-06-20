package com.sia.soapclient;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceFeature;
import jakarta.xml.ws.Service;

@WebServiceClient(name = "XmlPortService",
                  wsdlLocation = "file:src/main/resources/wsdl/xmlService.wsdl",
                  targetNamespace = "http://soap.sia.com/")
public class XmlPortService extends Service {

    public static final URL WSDL_LOCATION;

    public static final QName SERVICE = new QName("http://soap.sia.com/", "XmlPortService");
    public static final QName XmlPortSoap11 = new QName("http://soap.sia.com/", "XmlPortSoap11");
    static {
        URL url = null;
        try {
            url = URI.create("file:src/main/resources/wsdl/xmlService.wsdl").toURL();
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(XmlPortService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "file:src/main/resources/wsdl/xmlService.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public XmlPortService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public XmlPortService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public XmlPortService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public XmlPortService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public XmlPortService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public XmlPortService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    @WebEndpoint(name = "XmlPortSoap11")
    public XmlPort getXmlPortSoap11() {
        return super.getPort(XmlPortSoap11, XmlPort.class);
    }

    @WebEndpoint(name = "XmlPortSoap11")
    public XmlPort getXmlPortSoap11(WebServiceFeature... features) {
        return super.getPort(XmlPortSoap11, XmlPort.class, features);
    }

}
