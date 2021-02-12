package com.example.demo.controllers;

import com.example.demo.entity.Client;
import com.example.demo.entity.Demonstration;
import com.example.demo.entity.Prestation;
import com.example.demo.entity.TypeService;
import com.example.demo.service.ClientService;
import com.example.demo.service.PrestationService;
import com.example.demo.service.TypeServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/prestation")

public class PrestationController {
    @Autowired
    PrestationService Service;
    @Autowired
    TypeServiceService TypeService;
    @GetMapping
    public ResponseEntity<List<Prestation>> findAll()

             {
        return ResponseEntity.status(HttpStatus.OK).body(Service.findAll());
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Prestation> create(@RequestParam Long id , @RequestParam String titre, @RequestParam String description, @RequestParam Double prix, @RequestParam String adresse, @RequestParam Integer capacite , @RequestParam Boolean livrable , @RequestParam String type ,@RequestBody MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(Service.create(id,titre,description,prix,adresse,capacite,livrable,type,file));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Prestation> update(@PathVariable Long id,@RequestBody Prestation prestation) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.update(id, prestation));
    }

    @GetMapping(value = "/Get/{id}")
    public ResponseEntity<Prestation> GetByID(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.finfbyid(id));
    }

    @GetMapping("/types")
    public ResponseEntity<List<TypeService>> findByPrestation()
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(TypeService.findAll());
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.delete(id));
    }


}
