package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date activity_schedule;

    @Size(max = 50)
    private String promotion;

    private String file;

    private Boolean available;

    @Transient
    private String fileURL;

    public Activity() {
    }

    public Activity( String name, Date activity_schedule, String promotion, Boolean available, String file) {

        this.name = name;
        this.activity_schedule = activity_schedule;
        this.promotion = promotion;
        this.file = file;
        this.available = available;
    }

    public Activity(String file) {
        this.file = file;

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


    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }
}




