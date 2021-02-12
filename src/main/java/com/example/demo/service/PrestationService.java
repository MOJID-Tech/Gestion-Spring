package com.example.demo.service;

import com.example.demo.entity.Client;
import com.example.demo.entity.Demonstration;
import com.example.demo.entity.Prestation;
import com.example.demo.entity.TypeService;
import com.example.demo.exception.CredentialAlreadyExistsException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NullValueException;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.PrestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class PrestationService {

    @Autowired
    private PrestationRepository repository;
    @Autowired
    private TypeServiceService typeServiceService;
    @Autowired
    private ClientService  clientService;
    @Autowired
    private DemonstrationService demonstrationService;
    @Transactional(readOnly = true)
    public Prestation finfbyid(Long  id ){
        Prestation prestation;
        return  prestation = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Prestation.class, id));
    }
    @Transactional(readOnly = true)
    public Page<Prestation> findAll(String pageIndex1, String size1) {
        System.out.println("helooPrestation");
        int pageIndex= Integer.parseInt(pageIndex1);
        int size= Integer.parseInt(size1);
        Pageable pageable = (Pageable) PageRequest.of(pageIndex,  size, Sort.Direction.DESC, "titre");
        return repository.findAll(pageable);

    }
    @Transactional(readOnly = true)
    public List<Prestation> findAll() {
        System.out.println("helooPrestation22");
        List<Prestation> prestations;
        return  prestations = repository.findAll();

    }

    @Transactional(readOnly = false)
    public Prestation create(Long id  , String titre , String description  , Double prix , String adresse , Integer capacite , Boolean livrable , String type, MultipartFile files  ) throws IOException {
        if (  titre== null) {
            throw new NullValueException(titre);
        }
        if (  description == null) {
            throw new NullValueException(description);
        }
        if (adresse == null) {
            throw new NullValueException(adresse);
        }
        /*if (prix == null ){
            throw new NullValueException(prix);
        }*/
        Client client ;
        client = clientService.findbyId(id);

        if (repository.findByTitreAndClient(titre, client).isPresent()) {
            throw new CredentialAlreadyExistsException("Titre" );
        }

        Prestation prestation = new Prestation();
       // for(MultipartFile file : files) {
            Demonstration demonstration = demonstrationService.uplaodImage2(files);
            demonstration.setPrestation(prestation);
            Set setdemo = prestation.getDemonstrations();
            setdemo.add(demonstration);
        //}
        prestation.setAdresse(adresse);
        prestation.setCapacite(capacite);
        prestation.setDescription(description);
        prestation.setLivrable(livrable);
        prestation.setClient(client);
        prestation.setPrix(prix);
        prestation.setTitre(titre);
        TypeService typeservice = typeServiceService.finfbyNom(type);
        prestation.setTypeService(typeservice);


        Prestation savedprestation = repository.save(prestation);


        return savedprestation;

    }
    //Update
    @Transactional(readOnly = false)
    public Prestation update( Long id ,Prestation prestation) {
        if (prestation == null) {
            throw new NullValueException("prestation");
        }
        if (prestation.getId() == null) {
            throw new NullValueException("id");
        }
        if (!repository.existsById(id)) {
            throw new NotFoundException(Prestation.class, id);
        }
        Prestation prestation1 = finfbyid(id);
        prestation1.setAdresse(prestation.getAdresse());
        prestation1.setCapacite(prestation.getCapacite());
        prestation1.setDescription(prestation.getDescription());
        prestation1.setLivrable(prestation.getLivrable());
        prestation1.setPrix(prestation.getPrix());
        prestation1.setTitre(prestation.getTitre());

        return prestation1;
    }
    // delet
    @Transactional(readOnly = false)
    public Boolean delete(Long id) {
        if (id == null) {
            throw new NullValueException("id");
        }


        Prestation prestation = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Prestation.class, id));
        Set demonstrations= prestation.getDemonstrations();
        Iterator it = demonstrations.iterator();
        while(it.hasNext())
        demonstrationService.delete((Long) it.next());



        repository.delete(prestation);
        return true;
    }


}
