package com.sia.rest.usecase.filter;


import com.sia.rest.usecase.filter.dto.FilterDocumentsRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.sia.rest.entity.Document;

public interface FilterDocumentsUseCase {
    Page<Document> execute(FilterDocumentsRequest request, Pageable pageable);
}