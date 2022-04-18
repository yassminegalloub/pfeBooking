package com.bezkoder.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(	name = "Activity",
        uniqueConstraints = { @UniqueConstraint(columnNames = "name") }
)

public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String name ;

    private Date activity_schedule;


    @Size(max = 50)
    private String promotion;

    @Column( length = 64)
    private String picture;


    private Boolean available;

    public Activity() {
    }

    public Activity( String name, Date activity_schedule, String promotion, String picture, Boolean available) {

        this.name = name;
        this.activity_schedule = activity_schedule;
        this.promotion = promotion;
        this.picture = picture;
        this.available = available;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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




