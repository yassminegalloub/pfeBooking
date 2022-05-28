package com.bezkoder.springjwt.DTO;

import javax.validation.constraints.NotBlank;

public class CatalogDto {
    @NotBlank
    private String name_catalog;
    private String promotion;


    public CatalogDto() {
    }

    public CatalogDto(String name_catalog, String promotion) {
        this.name_catalog = name_catalog;
        this.promotion = promotion;
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
}
