package com.bezkoder.springjwt.DTO;

public class RoomDto {
    private String name_room;
    private String file;
    private String details;
    private String price;

    public RoomDto(String details, String name_room, String price, String file) {
        this.name_room = name_room;
        this.file = file;
        this.details = details;
        this.price = price;
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
}
