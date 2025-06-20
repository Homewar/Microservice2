package com.sia.rest.entity;

import com.sia.rest.adapter.LocalDateAdapter;
import com.sia.rest.annotation.ValidDate;
import com.sia.rest.annotation.ValidEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;

import java.time.LocalDate;


@Setter
@Getter
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Document {

    public enum DocumentType{
        PASSPORT,
        INTERNATIONAL_PASSPORT,
        DRIVER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "can't be null")
    private String series;

    @NotNull(message = "can't be null")
    private String number;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "can't be null")
    @ValidEnum(enumClass = DocumentType.class, message = "Valid values PASSPORT, INTERNATIONAL_PASSPORT, DRIVER.\n" +
            "\n")
    private DocumentType type;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @NotNull(message = "date can't be null")
    @ValidDate
    private LocalDate issueDate;
}
