package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "catalog")
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_catalog;
    private String name_catalog;
    private String promotion;

    @OneToMany(mappedBy = "catalog")

    private List<Product> products;
    public Catalog() {
    }

    public Catalog(int id_catalog, String name_catalog, String promotion) {
        this.id_catalog = id_catalog;
        this.name_catalog = name_catalog;
        this.promotion = promotion;
    }

    public Catalog(String name_catalog, String promotion) {
        this.name_catalog = name_catalog;
        this.promotion = promotion;
    }

    public int getId_catalog() {
        return id_catalog;
    }

    public void setId_catalog(int id_catalog) {
        this.id_catalog = id_catalog;
    }

    public String getName_catalog() {
        return name_catalog;
    }

    public void setName_catalog(String name_catalog) {
        this.name_catalog = name_catalog;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
