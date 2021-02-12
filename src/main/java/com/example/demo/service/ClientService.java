package com.example.demo.service;

import com.example.demo.entity.Client;
import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NullValueException;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service

public class ClientService {
    @Autowired
    private ClientRepository repository;
    @Autowired
    private UserService userService; ;


    @Transactional(readOnly = true)
    public List<Client> findAll() {

        List<Client> clients;
        return clients = repository.findAll();

    }

    @Transactional(readOnly = true)
    public Client findbyEmail(String email) {
        if (email == null) {
            throw new NullValueException("login");
        }
        Client salarie;
        return salarie = repository.findByEmailclient(email)
                .
                        orElseThrow(() -> new NotFoundException(Client.class, "email", email));
    }
    @Transactional(readOnly = true)
    public Client findbyId(Long id) {
        if (id == null) {
            throw new NullValueException("id");
        }
        Client client;
        return client = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Client.class,id ));
    }

    @Transactional(readOnly = false)
    public Client create(String nom, String prenom, String email, String tel, String adresse,String datenaissance,String password) {
        User user=null;
        String login=email;
        Client newclient = null;
        if (nom == null || prenom==null) {
            throw new NullValueException(" nom ou prénom est vide");
        }

        if (tel==null) {
            throw new NullValueException(" tel est vide");
        }
        //user=userService.findByLogin(login);
        //if(user!=null)

        Date date1 =  new Date();
        try {
            date1=new SimpleDateFormat("yyyy-mm-dd").parse(datenaissance);
            System.out.println(date1); // Sat Jan 02 00:00:00 GMT 2010
        }catch
        (ParseException e)
        {

        }
        System.out.println("hello");
        user=userService.createUser(login,password,email);
        if(user!=null) {
            newclient = new Client(nom, prenom,email,tel,adresse,date1);
            repository.save(newclient);
            user.setClient(newclient);

        }
        return newclient;
    }


}
