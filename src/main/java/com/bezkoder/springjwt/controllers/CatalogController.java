package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.DTO.CatalogDto;
import com.bezkoder.springjwt.DTO.Message;
import com.bezkoder.springjwt.models.Catalog;
import com.bezkoder.springjwt.security.services.CatService;
import com.bezkoder.springjwt.security.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/catalog")
@Component
public class CatalogController {

    @Autowired
    CatalogService catalogService;
    @Autowired
    CatService catservice;


    @GetMapping("/list")
    public ResponseEntity<List<Catalog>> list(){
        List<Catalog> list = catalogService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @GetMapping("/details/{id_catalog}")
    public ResponseEntity<Catalog> getById(@PathVariable("id_catalog") Integer id_catalog){
        if (!catalogService.existsById(id_catalog))
            return new ResponseEntity(new Message("not exist"),HttpStatus.NOT_FOUND);
        Catalog catalog = catalogService.getOne(id_catalog).get();
        return new ResponseEntity(catalog, HttpStatus.OK);
    }

    @PostMapping("ajout")
    public ResponseEntity<?>create(@RequestBody CatalogDto catalogDto){
        if(catalogDto.getName_catalog()==null)
            return new ResponseEntity(new Message("enter le nom"),HttpStatus.BAD_REQUEST);
        if (catalogDto.getPromotion()==null)
            return new ResponseEntity(new Message("promotion"),HttpStatus.BAD_REQUEST);

        Catalog catalog= new Catalog(catalogDto.getName_catalog(), catalogDto.getPromotion());
        catalogService.save(catalog);
        return new ResponseEntity(new Message("Catalogue enregister avec succees"),HttpStatus.OK);
    }


    @PutMapping("/update/{id_catalog}")
    public  ResponseEntity<?> update(@PathVariable("id_catalog") int id_catalog, @RequestBody() CatalogDto catalogDto){

        if (!catalogService.existsById(id_catalog))
            return new ResponseEntity(new Message("not exist"),HttpStatus.NOT_FOUND);

        if (catalogDto.getName_catalog()==null)
            return new ResponseEntity(new Message("enter le nom obligatoire"),HttpStatus.BAD_REQUEST);
        if (catalogDto.getPromotion()==null)
            return new ResponseEntity(new Message("enter le prix obligatoire"),HttpStatus.BAD_REQUEST);

        Catalog catalog = catalogService.getOne(id_catalog).get();
        catalog.setName_catalog(catalogDto.getName_catalog());
        catalog.setPromotion(catalogDto.getPromotion());
        catalogService.save(catalog);

        return new ResponseEntity(new Message("  updated succfuly"),HttpStatus.OK);



    }

    @DeleteMapping("/delete/{id_catalog}")
    public ResponseEntity<?> delete(@PathVariable("id_catalog")int id_catalog){
        if(!catalogService.existsById(id_catalog))
            return new ResponseEntity(new Message("not existe"), HttpStatus.NOT_FOUND);
        catalogService.delete(id_catalog);
        return new ResponseEntity(new Message("produit supprimer avec succee"), HttpStatus.OK);
    }


    @PutMapping("/ProductCatalog/{id_product}/{id_catalog}")
    @ResponseBody
    public void ProductCatalog(@PathVariable("id_product") int id_product, @PathVariable("id_catalog") int id_catalog){
        catservice.ProductCatalog(id_product,id_catalog);
    }
}
