package com.bezkoder.springjwt.models;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Table(	name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String details;

    private String name_room;

    private String price;

    private String file;
    private Boolean status;

    @Transient
    private String fileURL;

    public Room() {

    }

    public Room( String details, String name_room, String price,Boolean status,String file) {
        this.name_room = name_room;
        this.file= file;
        this.details = details;
        this.price = price;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName_room() {
        return name_room;
    }

    public void setName_room(String name_room) {
        this.name_room = name_room;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getFileURL() { return fileURL; }

    public void setFileURL(String fileURL) { this.fileURL = fileURL; }
}
