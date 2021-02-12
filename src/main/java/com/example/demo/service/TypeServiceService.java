package com.example.demo.service;

import com.example.demo.entity.Prestation;
import com.example.demo.entity.TypeService;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.TypeServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class TypeServiceService {

    @Autowired
    private TypeServiceRepository repository;

    @Transactional(readOnly = true)
    public TypeService finfbyId(Long  id ){
        TypeService typeService;
        return  typeService  =  repository.findById(id)
                .orElseThrow(() -> new NotFoundException(TypeService.class, id));
    }
    @Transactional(readOnly = true)
    public TypeService finfbyNom(String nom){
        TypeService typeService;
        return  typeService  =  repository.findByNom(nom)
        .orElseThrow(() -> new NotFoundException(TypeService.class, "nom", nom));
    }
    @Transactional(readOnly = true)
    public List<TypeService>   findAll(){
        List<TypeService> typeServices;
        return   typeServices=repository.findAll();
    }


}
