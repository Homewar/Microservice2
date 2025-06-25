package com.sia.rest.usecase.impl.filter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sia.rest.entity.Person;
import com.sia.rest.repository.PersonRepository;
import com.sia.rest.usecase.filter.FilterPersonsUseCase;
import com.sia.rest.usecase.filter.dto.FilterPersonsRequest;

@Service
public class FilterPersonsUseCaseImpl implements FilterPersonsUseCase {

    private final PersonRepository personRepo;

    public FilterPersonsUseCaseImpl(PersonRepository personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public Page<Person> execute(FilterPersonsRequest req, Pageable pageable) {
        Specification<Person> spec = null;

        // Фильтрация по имени (частичное совпадение, без учёта регистра)
        if (req.getName() != null) {
            Specification<Person> s = (root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + req.getName().toLowerCase() + "%");
            spec = (spec == null ? s : spec.and(s));
        }

        // По фамилии
        if (req.getSurname() != null) {
            Specification<Person> s = (root, query, cb) ->
                    cb.like(cb.lower(root.get("surname")), "%" + req.getSurname().toLowerCase() + "%");
            spec = (spec == null ? s : spec.and(s));
        }

        // По отчеству
        if (req.getPatronymic() != null) {
            Specification<Person> s = (root, query, cb) ->
                    cb.like(cb.lower(root.get("patronymic")), "%" + req.getPatronymic().toLowerCase() + "%");
            spec = (spec == null ? s : spec.and(s));
        }

        // Диапазон даты рождения
        if (req.getBirthDateFrom() != null && req.getBirthDateTo() != null) {
            Specification<Person> s = (root, query, cb) ->
                    cb.between(root.get("birthDate"),
                            req.getBirthDateFrom(),
                            req.getBirthDateTo());
            spec = (spec == null ? s : spec.and(s));
        } else {
            if (req.getBirthDateFrom() != null) {
                Specification<Person> s = (root, query, cb) ->
                        cb.greaterThanOrEqualTo(root.get("birthDate"),
                                req.getBirthDateFrom());
                spec = (spec == null ? s : spec.and(s));
            }
            if (req.getBirthDateTo() != null) {
                Specification<Person> s = (root, query, cb) ->
                        cb.lessThanOrEqualTo(root.get("birthDate"),
                                req.getBirthDateTo());
                spec = (spec == null ? s : spec.and(s));
            }
        }

        // По полу (равенство)
        if (req.getGender() != null) {
            Specification<Person> s = (root, query, cb) ->
                    cb.equal(root.get("gender"), req.getGender());
            spec = (spec == null ? s : spec.and(s));
        }

        // Диапазон createdAt
        if (req.getCreatedFrom() != null && req.getCreatedTo() != null) {
            Specification<Person> s = (root, query, cb) ->
                    cb.between(root.get("createdAt"),
                            req.getCreatedFrom(),
                            req.getCreatedTo());
            spec = (spec == null ? s : spec.and(s));
        } else {
            if (req.getCreatedFrom() != null) {
                Specification<Person> s = (root, query, cb) ->
                        cb.greaterThanOrEqualTo(root.get("createdAt"),
                                req.getCreatedFrom());
                spec = (spec == null ? s : spec.and(s));
            }
            if (req.getCreatedTo() != null) {
                Specification<Person> s = (root, query, cb) ->
                        cb.lessThanOrEqualTo(root.get("createdAt"),
                                req.getCreatedTo());
                spec = (spec == null ? s : spec.and(s));
            }
        }

        return personRepo.findAll(spec, pageable);
    }
}