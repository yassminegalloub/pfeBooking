package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Product;
import com.bezkoder.springjwt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> list(){
        return productRepository.findAll();
    }

    public Optional<Product> getOne(int id){
        return productRepository.findById(id);
    }

    public Optional<Product> getByCode(String code){
        return productRepository.findByCode(code);
    }

    public void save(Product product){
        productRepository.save(product);
    }

    public void delete(int id){
        productRepository.deleteById(id);
    }

    public boolean existsById (int id){
        return productRepository.existsById(id);
    }

    public boolean existsByCode (String code){
        return productRepository.existsByCode(code);
    }

}
