package com.example.demo.repository;

import com.example.demo.entity.Client;
import com.example.demo.entity.Role;
import com.example.demo.entity.TypeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer>, JpaSpecificationExecutor<Client> {
    Optional<Client> findByEmailclient(String email_client);
    Optional<Client> findById(Long id);

}
