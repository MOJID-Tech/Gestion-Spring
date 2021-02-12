package com.example.demo.controllers;

import com.example.demo.entity.User;
//import com.example.demo.entity.UserChangePasswordDTO;
//import com.example.demo.entity.ValidationEmailDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin ("*")
@RestController
@RequestMapping(value="/users")

public class UserController {

    @Autowired
    UserService Service;
    @GetMapping
    public ResponseEntity<Page<User>> findAll(

            @RequestParam(required = false, defaultValue = "0") String pageIndex,
            @RequestParam(required = false, defaultValue = "2") String size) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.findAll(pageIndex, size));
    }


    @PostMapping(value = "/authenticate")
    public ResponseEntity<User> authenticate(
            @RequestParam String login, @RequestParam String password) {

        return ResponseEntity.status(HttpStatus.OK).body(Service.authenticate(login,password));
    }
   /* @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.findById(id));
    }
    @GetMapping(value = "/user")
    public ResponseEntity<User> findBylogin( @RequestParam String login) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.findByLogin(login));
    }
    @GetMapping(value = "/{id}/roles")
    public ResponseEntity<List<String>> getroles(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.getroles(id));
    }


    @PostMapping
    public ResponseEntity<User> create(@RequestParam String login, @RequestParam String password,@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.CREATED).body(Service.createUser(login,password,email));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id,@RequestParam String login ,@RequestParam String password) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.updateUser(id, login,password));
    }
    @PostMapping (value = "/{id}/addrole")
    public ResponseEntity<User> addrole(@PathVariable Integer id,@RequestParam String role) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.addrole(id,role));
    }
    @DeleteMapping (value = "/{id}")
    public ResponseEntity<Boolean> removerole(@PathVariable Integer id,@RequestParam String role) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.removerole(id,role));
    }





    @PutMapping(value = "/changepassword")

    public ResponseEntity<User> changePassword(@RequestBody UserChangePasswordDTO user) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(Service.changePassword(getLogin(), user.getCurrentPassword(), user.getNewPassword()));
    }

    @PostMapping("/{id}/reset-password")
    public ResponseEntity<ValidationEmailDTO> resetPassword(@PathVariable Integer id, @RequestBody UserChangePasswordDTO user) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.resetPassword(id, user.getNewPassword()));
    }
    @PostMapping("/reset-password")
    public ResponseEntity<ValidationEmailDTO> resetPassword2(@RequestParam String email,@RequestParam String login) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.resetPassword(email , login));
    }

    @PostMapping(value = "/{id}/activate")
    public ResponseEntity<User> activate(@PathVariable Integer id ,@RequestParam String active) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.setActive(id, active));
    }

    @PostMapping(value = "/{id}/deactivate")
    public ResponseEntity<User> deactivate(@PathVariable Integer id ,@RequestParam String active) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.setActive(id, active));
    }
    protected String getLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null ? authentication.getName() : null;
    }*/

}
