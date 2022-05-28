package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Catalog;
import com.bezkoder.springjwt.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CatalogService {
    public boolean existsById;
    @Autowired
    CatalogRepository catalogRepository;
    public List<Catalog> list(){ return catalogRepository.findAll();}
    public void save(Catalog catalog){ catalogRepository.save(catalog);}
    public void delete(int id_catalog){catalogRepository.deleteById(id_catalog);}
    public boolean existsById (int id_catalog){
        return catalogRepository.existsById(id_catalog);
    }
    public Optional<Catalog> getOne(int id_catalog){
        return catalogRepository.findById(id_catalog);

    }
}
