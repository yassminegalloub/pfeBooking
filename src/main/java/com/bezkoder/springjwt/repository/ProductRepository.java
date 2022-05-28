package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByCode(String code);
    boolean existsByCode(String code);

}
