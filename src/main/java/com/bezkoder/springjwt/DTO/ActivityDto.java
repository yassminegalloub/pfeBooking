package com.bezkoder.springjwt.DTO;

import com.bezkoder.springjwt.models.Activity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.Date;

public class ActivityDto {


    private String name;

    private Date activity_schedule;

    private String promotion;
    private Boolean available;

    private String file;


    public ActivityDto() {
    }

    public ActivityDto(String name, Date activity_schedule, String promotion, Boolean available, String file) {
        this.name = name;
        this.activity_schedule = activity_schedule;
        this.promotion = promotion;
        this.available = available;
        this.file = file;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getActivity_schedule() {
        return activity_schedule;
    }

    public void setActivity_schedule(Date activity_schedule) {
        this.activity_schedule = activity_schedule;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getFile() {return file;}

    public void setFile(String file) {this.file = file;}

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }


}
