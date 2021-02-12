package com.example.demo.repository;

import com.example.demo.entity.Demonstration;
import com.example.demo.entity.Prestation;
import com.example.demo.entity.TypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DemonstrationRepository  extends JpaRepository<Demonstration, Long>, JpaSpecificationExecutor<Demonstration> {
    Optional<Demonstration> findById(Long id);
    Optional<Demonstration> findByName(String nom);
    Page<Demonstration> findAll(Pageable pageable);
    @Query(value = "select distinct de.* from demonstration de inner join prestation d on de.prestation_id = d.id WHERE d.id = :prestation_id "
            ,nativeQuery = true)
    List<Demonstration> findByPrestation(@Param("prestation_id") Long prestation_id);

}
