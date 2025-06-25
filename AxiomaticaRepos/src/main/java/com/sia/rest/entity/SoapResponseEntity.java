package com.sia.rest.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "soap_responses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SoapResponseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "response_body", columnDefinition = "TEXT")
    private String responseBody;

    @Column(name = "received_at")
    private java.time.LocalDateTime receivedAt;
}