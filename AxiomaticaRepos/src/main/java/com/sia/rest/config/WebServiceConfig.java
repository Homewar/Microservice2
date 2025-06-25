package com.sia.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class WebServiceConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller m = new Jaxb2Marshaller();
        m.setContextPath("com.sia.soapclient");
        return m;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller) {
        WebServiceTemplate tpl = new WebServiceTemplate();
        tpl.setMarshaller(marshaller);
        tpl.setUnmarshaller(marshaller);
        tpl.setDefaultUri("http://localhost:8080/ws");
        return tpl;
    }
}