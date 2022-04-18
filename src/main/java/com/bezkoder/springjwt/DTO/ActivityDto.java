package com.bezkoder.springjwt.DTO;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class ActivityDto {

    @NotBlank
    private String name ;

    private Date activity_schedule;

    private String promotion;

    private String picture;


    private Boolean available;

    public ActivityDto() {
    }

    public ActivityDto(String name, Date activity_schedule, String promotion, String picture, Boolean available) {
        this.name = name;
        this.activity_schedule = activity_schedule;
        this.promotion = promotion;
        this.picture = picture;
        this.available = available;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
