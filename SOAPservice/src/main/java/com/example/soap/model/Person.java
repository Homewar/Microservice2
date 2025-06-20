package com.example.soap.model;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Person {
    private String name;
    private String surname;
    private String patronymic;
    private String birthDate;
    private String gender;
    private Document document;

    @XmlAccessorType(XmlAccessType.FIELD)
    @Getter
    @Setter
    public static class Document {
        private String series;
        private String number;
        private String type;
        private String issueDate;
    }
}