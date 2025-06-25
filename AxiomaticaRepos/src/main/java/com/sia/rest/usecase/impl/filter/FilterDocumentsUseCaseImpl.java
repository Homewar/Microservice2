package com.sia.rest.usecase.impl.filter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sia.rest.entity.Document;
import com.sia.rest.repository.DocumentRepository;
import com.sia.rest.usecase.filter.FilterDocumentsUseCase;
import com.sia.rest.usecase.filter.dto.FilterDocumentsRequest;

@Service
public class FilterDocumentsUseCaseImpl implements FilterDocumentsUseCase {

    private final DocumentRepository documentRepo;

    public FilterDocumentsUseCaseImpl(DocumentRepository documentRepo) {
        this.documentRepo = documentRepo;
    }

    @Override
    public Page<Document> execute(FilterDocumentsRequest req, Pageable pageable) {
        Specification<Document> spec = null;

        if (req.getType() != null) {
            Specification<Document> s = (root, query, cb) ->
                    cb.equal(root.get("type"), req.getType());
            spec = (spec == null ? s : spec.and(s));
        }

        if (req.getSeries() != null) {
            Specification<Document> s = (root, query, cb) ->
                    cb.equal(root.get("series"), req.getSeries());
            spec = (spec == null ? s : spec.and(s));
        }

        if (req.getNumber() != null) {
            Specification<Document> s = (root, query, cb) ->
                    cb.equal(root.get("number"), req.getNumber());
            spec = (spec == null ? s : spec.and(s));
        }

        if (req.getIssueDateFrom() != null && req.getIssueDateTo() != null) {
            Specification<Document> s = (root, query, cb) ->
                    cb.between(root.get("issueDate"),
                            req.getIssueDateFrom(),
                            req.getIssueDateTo());
            spec = (spec == null ? s : spec.and(s));
        } else {
            if (req.getIssueDateFrom() != null) {
                Specification<Document> s = (root, query, cb) ->
                        cb.greaterThanOrEqualTo(root.get("issueDate"),
                                req.getIssueDateFrom());
                spec = (spec == null ? s : spec.and(s));
            }
            if (req.getIssueDateTo() != null) {
                Specification<Document> s = (root, query, cb) ->
                        cb.lessThanOrEqualTo(root.get("issueDate"),
                                req.getIssueDateTo());
                spec = (spec == null ? s : spec.and(s));
            }
        }

        if (req.getCreatedFrom() != null && req.getCreatedTo() != null) {
            Specification<Document> s = (root, query, cb) ->
                    cb.between(root.get("createdAt"),
                            req.getCreatedFrom(),
                            req.getCreatedTo());
            spec = (spec == null ? s : spec.and(s));
        } else {
            if (req.getCreatedFrom() != null) {
                Specification<Document> s = (root, query, cb) ->
                        cb.greaterThanOrEqualTo(root.get("createdAt"),
                                req.getCreatedFrom());
                spec = (spec == null ? s : spec.and(s));
            }
            if (req.getCreatedTo() != null) {
                Specification<Document> s = (root, query, cb) ->
                        cb.lessThanOrEqualTo(root.get("createdAt"),
                                req.getCreatedTo());
                spec = (spec == null ? s : spec.and(s));
            }
        }

        // Если ни одного фильтра не задано, spec останется null и будут возвращены все документы
        return documentRepo.findAll(spec, pageable);
    }
}
