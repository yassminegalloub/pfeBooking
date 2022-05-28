package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CatalogRepository extends JpaRepository<Catalog, Integer> {

    Optional<Catalog> findById(Integer id_catalog);
}
