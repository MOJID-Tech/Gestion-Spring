package com.example.demo.repository;

import com.example.demo.entity.TypeService;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface TypeServiceRepository  extends JpaRepository<TypeService, Long>, JpaSpecificationExecutor<TypeService> {

    Optional<TypeService> findById(Long id);
    Optional<TypeService> findByNom(String nom);
}
