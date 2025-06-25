package com.sia.rest.entity;

import com.sia.rest.adapter.LocalDateAdapter;
import com.sia.rest.annotation.ValidDate;
import com.sia.rest.annotation.ValidEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Id;

@Entity
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class Person {
    public enum Gender{
        MAN,
        WOMAN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "can't be null")
    private String name;
    @NotEmpty(message = "can't be null")
    private String surname;
    @NotEmpty(message = "can't be null")
    private String patronymic;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @ValidDate
    private LocalDate birthDate;

    @XmlElement
    @ValidEnum(enumClass = Gender.class, message = "valid values MAN или WOMAN")
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
