package com.sia.rest.usecase.save.dto;

import lombok.Getter;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.AllArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class ProcessPersonResponse {
    private final String rawXml;
    private final LocalDateTime receivedAt;
}