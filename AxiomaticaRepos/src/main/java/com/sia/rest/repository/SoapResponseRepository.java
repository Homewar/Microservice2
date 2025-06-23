package com.sia.rest.repository;

import com.sia.rest.entity.SoapResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoapResponseRepository extends JpaRepository<SoapResponseEntity, Long> {
}