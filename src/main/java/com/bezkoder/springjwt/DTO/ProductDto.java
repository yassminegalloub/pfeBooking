package com.bezkoder.springjwt.DTO;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
public class ProductDto {
    @NotBlank
    private String code;
    @Min(0)
    private String name;

    public ProductDto() {
    }

    public ProductDto(String code, String name) {
        this.code = code;
        this.name = name;
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
}
