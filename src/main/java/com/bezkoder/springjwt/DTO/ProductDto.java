package com.bezkoder.springjwt.DTO;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
public class ProductDto {

    @NotBlank
    private String code;
    @Min(0)
    private String name;

    private String price;

    public ProductDto() {
    }

    public ProductDto(String code, String name, String price) {
        this.code = code;
        this.name = name;
        this.price = price;

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
}
