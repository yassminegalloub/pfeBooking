package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Catalog;
import com.bezkoder.springjwt.models.Product;
import com.bezkoder.springjwt.repository.CatalogRepository;
import com.bezkoder.springjwt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogImpl implements CatService{
    @Autowired
    CatalogRepository catRepository;
    @Autowired
    ProductRepository prodRepository;
    @Override
    public void ProductCatalog(Integer id_product, Integer id_catalog) {

        Catalog catalogue = catRepository.findById(id_catalog).get();
        Product prod= prodRepository.findById(id_product).get();
        prod.setCatalog(catalogue);
        prodRepository.save(prod);



    }
}
