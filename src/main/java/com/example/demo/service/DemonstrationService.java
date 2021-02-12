package com.example.demo.service;
import java.io.ByteArrayOutputStream;

import java.io.IOException;

import java.util.*;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import com.example.demo.entity.Prestation;
import com.example.demo.exception.NullValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.entity.Demonstration;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.DemonstrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;

@Service
public class DemonstrationService {
    @Autowired
    private DemonstrationRepository imageRepository;
    @Autowired
    private PrestationService prestationService;


    @Transactional(readOnly = true)
    public Demonstration finfbyid(Long  id ){
        Demonstration demonstration;
        return  demonstration = imageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Demonstration.class, id));
    }
    @Transactional(readOnly = true)
    public Page<Demonstration> findAll(String pageIndex1, String size1) {

        int pageIndex= Integer.parseInt(pageIndex1);
        int size= Integer.parseInt(size1);
        Pageable pageable = (Pageable) PageRequest.of(pageIndex,  size, Sort.Direction.DESC, "name");
        return imageRepository.findAll(pageable);

    }
    @Transactional(readOnly = true)
    public List<Demonstration> findAllDemo() {

        return imageRepository.findAll();

    }

    @Transactional(readOnly = true)
    public List<Demonstration> findAllDemoone() {
      List<Demonstration> demos = new ArrayList<Demonstration>();
      List<Demonstration> demonstrations = imageRepository.findAll();
      List<Long> numeroprest = new ArrayList<Long>();
      for(int i=0;i<demonstrations.size();i++)
      {
          int index=numeroprest.indexOf(demonstrations.get(i).getPrestation().getId());
          if(index == -1) {
              System.out.println("baybay cv");
              System.out.println("idd : "+demonstrations.get(i).getPrestation().getId());
              numeroprest.add(demonstrations.get(i).getPrestation().getId());
                demos.add(demonstrations.get(i));
            }
      }



      return demos;

    }



    @Transactional(readOnly = true)
    public List<Demonstration> finddemobytype(String type) {
        List<Demonstration> images=imageRepository.findAll();
        List<Demonstration> trouve = new ArrayList<Demonstration>();
        for(int i=0;i<images.size();i++) {

            System.out.println(images.get(i).toString());

               if (images.get(i).getPrestation().getTypeService().getNom().equals(type)) {
                   trouve.add(images.get(i));
               }
           }
/*
        System.out.print("salammmmmmmmm");
        for(int i=0;i<trouve.size();i++)
            System.out.println(trouve.get(i));
*/
        return trouve;
    }

    @Transactional(readOnly = true)
    public List<Demonstration> finddemobyclient(String client) {
        List<Demonstration> images=imageRepository.findAll();
        List<Demonstration> trouve = new ArrayList<Demonstration>();
        for(int i=0;i<images.size();i++) {


            if(images.get(i).getPrestation().getClient().getNom_client().equals(client)){
                trouve.add(images.get(i));
            }
        }
      //  System.out.println(trouve);
/*        for(int i=0;i<trouve.size();i++)
            System.out.println(trouve.get(i));
*/

        return trouve;
    }
    @Transactional(readOnly = true)
    public List<Demonstration> finddemoByPrix(List<Demonstration> mylist, double prix) {
        List<Demonstration> trouve =new ArrayList<Demonstration>();
        System.out.println(" prix"+prix);
        for (int i = 0; i < mylist.size(); i++) {
                if(mylist.get(i).getPrestation().getPrix()<=prix)
                {
                    trouve.add(mylist.get(i));
                }
        }
      return trouve;
    }

        @Transactional(readOnly = true)
    public List<Demonstration> finddemo(String type, String  client, String prix) {
        System.out.println(" salam ");
        List<Demonstration> images=imageRepository.findAll();
        List<Demonstration> trouve =new ArrayList<Demonstration>();
        List<Demonstration> trouveclient=new ArrayList<Demonstration>();
        List<Demonstration> trouvetype=new ArrayList<Demonstration>();
           // System.out.println(" apres ");
            System.out.println(type+" "+client);

            if(type.equals("") && client.equals("")) {
                System.out.println("hello clien ");
                trouve = images;
            }
            else if(type!=null && client.equals("")) {
                trouvetype = this.finddemobytype(type);
                trouve = trouvetype;
                System.out.println("entree client null");
            }
            else if(client!=null && type.equals(""))
            {
                trouveclient = this.finddemobyclient(client);
                trouve = trouveclient;
                System.out.println("entree type null");

            }
           else
           {
               trouvetype = this.finddemobytype(type);
               trouveclient = this.finddemobyclient(client);


               for (int i = 0; i < trouvetype.size(); i++) {
                    if (trouveclient.indexOf(trouvetype.get(i)) != -1)
                        trouve.add(trouvetype.get(i));

                }
               System.out.println("entree type client");

           }
            System.out.println(" recherche trouve ");



            System.out.println(" final ");
            System.out.println(type+" "+client);
        /******      Search  Prix        ******/
            double prixs=Double.parseDouble(prix);

         if(prixs!=0.0)
         {
             System.out.println("enter prix");
             trouve=this.finddemoByPrix(trouve,prixs);

         }

        return trouve;

    }
    @Transactional(readOnly = true)
    public List<Demonstration> FindbyPrestation(Long id) {
        return imageRepository.findByPrestation(id);

    }
    @Transactional(readOnly = true)
    public List<Demonstration> FindbyIDPrestation(Long id) {
        List<Demonstration> demos=imageRepository.findByPrestation(id);
        List<Demonstration> trouve = null;
        for(int i=0;i<demos.size();i++)
        {
            if(demos.get(i).getPrestation().getId()==id)
                trouve.add(demos.get(i));
        }
      return trouve;
    }
    /*****ajout***/
    //@Autowired
   @Transactional(readOnly = false)
    public Demonstration uplaodImage(Long id , MultipartFile file) throws IOException {

        Prestation prestation = prestationService.finfbyid(id);

        System.out.println("Original Image Byte Size - " + file.getBytes().length);

        Demonstration img = new Demonstration(file.getOriginalFilename(), file.getContentType(),

                compressBytes(file.getBytes()));
         img.setPrestation(prestation);
        Demonstration save = imageRepository.save(img);

        return img ;

    }
    @Transactional(readOnly = false)
    public Demonstration uplaodImage2( MultipartFile file) throws IOException {



        System.out.println("Original Image Byte Size - " + file.getBytes().length);

        Demonstration img = new Demonstration(file.getOriginalFilename(), file.getContentType(),

                compressBytes(file.getBytes()));

        Demonstration save = imageRepository.save(img);

        return img ;

    }
  /*******************/
  @Transactional(readOnly = true)
    public Demonstration getImage( String imageName) throws IOException {

        final Optional<Demonstration> retrievedImage = imageRepository.findByName(imageName);

        Demonstration img = new Demonstration(retrievedImage.get().getName(), retrievedImage.get().getType(),

                decompressBytes(retrievedImage.get().getPicByte()));

        return img;

    }
    // compress the image bytes before storing it in the database

    public static byte[] compressBytes(byte[] data) {

        Deflater deflater = new Deflater();

        deflater.setInput(data);

        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        byte[] buffer = new byte[1024];

        while (!deflater.finished()) {

            int count = deflater.deflate(buffer);

            outputStream.write(buffer, 0, count);

        }

        try {

            outputStream.close();

        } catch (IOException e) {

        }

        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();

    }

    // uncompress the image bytes before returning it to the angular application

    public static byte[] decompressBytes(byte[] data) {

        Inflater inflater = new Inflater();

        inflater.setInput(data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        byte[] buffer = new byte[1024];

        try {

            while (!inflater.finished()) {

                int count = inflater.inflate(buffer);

                outputStream.write(buffer, 0, count);

            }

            outputStream.close();

        } catch (IOException ioe) {

        } catch (DataFormatException e) {

        }

        return outputStream.toByteArray();

    }
    //update
    @Transactional(readOnly = false)
    public Demonstration update(Long idDemonstration , MultipartFile file) throws IOException {

        Demonstration demonstration = finfbyid(idDemonstration);
        demonstration.setType(file.getOriginalFilename());
        demonstration.setName(file.getContentType());
        demonstration.setPicByte(compressBytes(file.getBytes()));

        return  demonstration;
    }

    @Transactional(readOnly = false)
    public Boolean delete(Long id) {
        if (id == null) {
            throw new NullValueException("id");
        }


        Demonstration demonstration = imageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Demonstration.class, id));


        imageRepository.delete(demonstration);
        return true;
    }
}
