package com.example.demo.repository;

//import com.example.demo.Salarie;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>{
    boolean existsById(Integer id);

    Optional<User> findById(Integer id);

    Optional<User> findByLogin(String id);
    //Optional<User> findBySalarieId(Long id);
    // Optional<User> findBySalarieIdAndLogin(Long id, String login);

    // @Query(value = "SELECT salarie_id FROM `user` WHERE id = :id "
    //        ,nativeQuery = true)
    //  Optional<Long> findSalarie(@Param("id") Integer id);


    //Optional<Salarie> findByEmailAndPhoneNumber(String email, String phoneNumber);
    Page<User> findAll(Pageable pageable);
    //   User user  findByRoleIdAndActive(USER, true);
}
