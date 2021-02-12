package com.example.demo.controllers;

import com.example.demo.entity.Demonstration;
import com.example.demo.entity.Prestation;
import com.example.demo.entity.User;
import com.example.demo.service.DemonstrationService;
import com.example.demo.service.PrestationService;
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
@RequestMapping(value="/demonstration")

public class DemonstrationController {
    @Autowired
    DemonstrationService Service;
   @GetMapping
    public ResponseEntity<Page<Demonstration>> findAll(

           @RequestParam(required = false, defaultValue = "0") String pageIndex,
            @RequestParam(required = false, defaultValue = "4") String size) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.findAll(pageIndex, size));
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Demonstration>> findByPrestation(@PathVariable(value = "id") Long idPrestation)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(Service.FindbyPrestation(idPrestation));

    }


    @GetMapping("/GetBy/{id}")
    public ResponseEntity<List<Demonstration>> findByPrestationByID(@PathVariable(value = "id") Long idPrestation)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(Service.FindbyIDPrestation(idPrestation));

    }


    @GetMapping("/GetDemonstrations")
    public ResponseEntity<List<Demonstration>> findAll()
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(Service.findAllDemoone());

    }

    @PostMapping(value = "/create/{id}")
    public ResponseEntity<Demonstration> create(@PathVariable Long id, @RequestBody MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(Service.uplaodImage(id,file));
    }
    @PutMapping(value = "/{idD}")
    public ResponseEntity<Demonstration> update(@PathVariable Long idD,@RequestBody MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(Service.update(idD, file));
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.delete(id));
    }

    @PostMapping (value = "/search")
    public ResponseEntity<List<Demonstration>> findAll(
            @RequestParam(required = false, defaultValue = "") String type, @RequestParam(required = false, defaultValue = "") String client,
            @RequestParam(required = false, defaultValue = "0") String prix)
            {
                return ResponseEntity.status(HttpStatus.OK).body(Service.finddemo(type, client,prix));
            }
}
