package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface PrestationRepository extends JpaRepository<Prestation, Long>, JpaSpecificationExecutor<Prestation> {
    Optional<Prestation> findByTitreAndClient(String titre , Client client );
    Page<Prestation> findAll(Pageable pageable);
    List<Prestation> findAll();
    Optional<Prestation> findById(Long id);
}
