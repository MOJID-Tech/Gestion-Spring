package com.example.demo.controllers;

import com.example.demo.entity.Client;
import com.example.demo.entity.User;
import com.example.demo.service.ClientService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/clients")

public class ClientController {

    @Autowired
    ClientService Service;

    @PostMapping(value = "/register")
    public ResponseEntity<Client> register(@RequestParam String nom, @RequestParam String prenom, @RequestParam String daten,@RequestParam String tel, @RequestParam String adresse, @RequestParam String email, @RequestParam String password) {
        return ResponseEntity.status(HttpStatus.CREATED).body(Service.create(nom,prenom,email,tel,adresse,daten,password));
    }

    @GetMapping(value = "/getclients")
    public ResponseEntity<List<Client>> getclients() {
        return ResponseEntity.status(HttpStatus.CREATED).body(Service.findAll());
    }

}
