
package com.sia.soapclient;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sia.soapclient package. 
 * <p>An ObjectFactory allows you to programmatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sia.soapclient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProcessXmlRequest }
     * 
     * @return
     *     the new instance of {@link ProcessXmlRequest }
     */
    public ProcessXmlRequest createProcessXmlRequest() {
        return new ProcessXmlRequest();
    }

    /**
     * Create an instance of {@link ProcessXmlResponse }
     * 
     * @return
     *     the new instance of {@link ProcessXmlResponse }
     */
    public ProcessXmlResponse createProcessXmlResponse() {
        return new ProcessXmlResponse();
    }

}
