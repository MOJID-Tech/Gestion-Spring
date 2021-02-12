package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.entity.user_role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface user_roleRepository extends  JpaRepository< user_role, Integer>, JpaSpecificationExecutor< user_role>  {
    boolean existsById(Long id);

    Optional<user_role> findById(Integer id);
    List<user_role> findByUserId(Integer UserId);

    user_role findByRoleIdAndUserId(Integer roleid, Integer userid);
    //Optional<Salarie> findByEmailAndPhoneNumber(String email, String phoneNumber);
    Page<user_role> findAll(Pageable pageable);


}
