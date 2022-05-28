package com.bezkoder.springjwt.controllers;

import org.apache.commons.lang3.StringUtils;
import com.bezkoder.springjwt.DTO.Message;
import com.bezkoder.springjwt.DTO.ProductDto;
import com.bezkoder.springjwt.models.Product;
import com.bezkoder.springjwt.security.services.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/liste")
    public ResponseEntity<List<Product>> list(){
        List<Product> list =productService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") int id){
        if (!productService.existsById(id))
            return new ResponseEntity(new Message("not exist"),HttpStatus.NOT_FOUND);
        Product product = productService.getOne(id).get();
        return new ResponseEntity(product, HttpStatus.OK);
    }


    @GetMapping("/detailscode/{code}")
    public ResponseEntity<Product> getByCode(@PathVariable("code") String code){
        if (!productService.existsByCode(code))
            return new ResponseEntity(new Message("not exist"),HttpStatus.NOT_FOUND);
        Product product = productService.getByCode(code).get();
        return new ResponseEntity(product, HttpStatus.OK);
    }

    @PostMapping("/create")
    public  ResponseEntity <?>create(@RequestBody ProductDto productDto){
        if (StringUtils.isBlank(productDto.getCode()))
            return new ResponseEntity(new Message("entrer le code oligatoire"),HttpStatus.BAD_REQUEST);
        if (productDto.getName()==null)
            return new ResponseEntity(new Message("enter le nom obligatoire"),HttpStatus.BAD_REQUEST);
        if (productDto.getPrice()==null)
            return new ResponseEntity(new Message("enter le prix obligatoire"),HttpStatus.BAD_REQUEST);
        if (productService.existsByCode(productDto.getCode()))
            return new ResponseEntity(new Message("le code est existe deja"),HttpStatus.BAD_REQUEST);

        Product product =new Product(productDto.getCode(), productDto.getName(), productDto.getPrice());
        productService.save(product);
        return new ResponseEntity(new Message("le produit cree avec succee"),HttpStatus.OK);



    }




    @PutMapping ("/update/{id}")
    public  ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProductDto productDto){

        if (!productService.existsById(id))
            return new ResponseEntity(new Message("not exist"),HttpStatus.NOT_FOUND);
        if (productService.existsByCode(productDto.getCode()) && productService.getByCode(productDto.getCode()).get().getId() !=id)
            return new ResponseEntity(new Message("le code est existe deja "),HttpStatus.BAD_REQUEST);

        if (StringUtils.isBlank(productDto.getCode()))
            return new ResponseEntity(new Message("entrer le code oligatoire"),HttpStatus.BAD_REQUEST);
        if (productDto.getName()==null)
            return new ResponseEntity(new Message("enter le nom obligatoire"),HttpStatus.BAD_REQUEST);
        if (productDto.getPrice()==null)
            return new ResponseEntity(new Message("enter le prix obligatoire"),HttpStatus.BAD_REQUEST);

        Product product = productService.getOne(id).get();
        product.setCode(productDto.getCode());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        productService.save(product);

        return new ResponseEntity(new Message("  updated succfuly"),HttpStatus.OK);



    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!productService.existsById(id))
            return new ResponseEntity(new Message("not existe"), HttpStatus.NOT_FOUND);
        productService.delete(id);
        return new ResponseEntity(new Message("produit supprimer avec succee"), HttpStatus.OK);
    }



}
