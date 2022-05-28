package com.bezkoder.springjwt.models;


import javax.persistence.*;


@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private String name;
    private String price;

    @ManyToOne
    // @JoinColumn(name = "catalog_id_catalog")
    private Catalog catalog;

    public Product() {
    }

    public Product(int id, String code, String name, String price) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public Product(String code, String name, String price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }
}

