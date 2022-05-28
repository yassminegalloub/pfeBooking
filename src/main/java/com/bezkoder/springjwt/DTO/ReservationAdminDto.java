package com.bezkoder.springjwt.DTO;

import com.bezkoder.springjwt.models.Activity;
import com.bezkoder.springjwt.models.Room;

import java.util.Date;

public class ReservationAdminDto {
    private Room room;

    private Long adult_number;

    private Long enfant_number ;

    private Date arrival;

    private Date departure ;

    private Activity activity;

    private String name;

    private String username;

    private String password;

    public ReservationAdminDto() {
    }

    public ReservationAdminDto(Room room, Long adult_number, Long enfant_number, Date arrival
            , Date departure, Activity activity, String name, String username, String password) {
        this.room = room;
        this.adult_number = adult_number;
        this.enfant_number = enfant_number;
        this.arrival = arrival;
        this.departure = departure;
        this.activity = activity;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Long getAdult_number() {
        return adult_number;
    }

    public void setAdult_number(Long adult_number) {
        this.adult_number = adult_number;
    }

    public Long getEnfant_number() {
        return enfant_number;
    }

    public void setEnfant_number(Long enfant_number) {
        this.enfant_number = enfant_number;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
