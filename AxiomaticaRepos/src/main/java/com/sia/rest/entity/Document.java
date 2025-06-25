package com.sia.rest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "document")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

    public enum DocumentType {
        PASSPORT,
        INTERNATIONAL_PASSPORT,
        DRIVER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "type can't be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private DocumentType type;

    @NotNull(message = "series can't be null")
    @Column(nullable = false)
    private String series;

    @NotNull(message = "number can't be null")
    @Column(nullable = false)
    private String number;

    @NotNull(message = "issueDate can't be null")
    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }

    /**
     * Для выдачи только типа в JSON-представлении Person
     */
    @JsonProperty("documentType")
    public String getDocumentTypeForJson() {
        return type != null ? type.name() : null;
    }
}
