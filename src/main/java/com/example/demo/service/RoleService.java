package com.example.demo.service;
//import com.example.demo.entity.Etat;
import com.example.demo.entity.Role;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NullValueException;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service

public class RoleService {
    @Autowired
    private RoleRepository repository;
    @Transactional(readOnly = true)
    public Role finfbyid(Integer id ){
        Role role;
        return  role  = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Role.class, id));
    }

    @Transactional(readOnly = true)
    public Role finfbyNom(String Nom ){
        Role role;
        return  role  = repository.findByNomRole(Nom)
                .orElseThrow(() -> new NotFoundException(Role.class, "nomRole" , Nom));
    }
}
