package com.example.demo.repository;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface RoleRepository extends  JpaRepository< Role, Integer>, JpaSpecificationExecutor< Role>  {
    Optional<Role> findById(Long id);
    Optional<Role> findByNomRole(String Nom);
}
