
package com.sia.soapclient;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;


public final class XmlPort_XmlPortSoap11_Client {

    private static final QName SERVICE_NAME = new QName("http://soap.sia.com/", "XmlPortService");

    private XmlPort_XmlPortSoap11_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = XmlPortService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        XmlPortService ss = new XmlPortService(wsdlURL, SERVICE_NAME);
        XmlPort port = ss.getXmlPortSoap11();

        {
        System.out.println("Invoking processXml...");
        com.sia.soapclient.ProcessXmlRequest _processXml_processXmlRequest = null;
        com.sia.soapclient.ProcessXmlResponse _processXml__return = port.processXml(_processXml_processXmlRequest);
        System.out.println("processXml.result=" + _processXml__return);


        }

        System.exit(0);
    }

}
