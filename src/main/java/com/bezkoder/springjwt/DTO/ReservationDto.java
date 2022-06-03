package com.bezkoder.springjwt.DTO;

import com.bezkoder.springjwt.models.Activity;
import com.bezkoder.springjwt.models.Room;
import com.bezkoder.springjwt.models.User;

import java.util.Date;

public class ReservationDto {
    private Long id_room;

    private Long adult_number;

    private Long enfant_number ;

    private Date arrival;

    private Date departure ;

    private Long id_activity;

     private Long id_user ;

     private boolean  is_reserved ;

    public ReservationDto() {
    }

    public ReservationDto(Long adult_number, Long enfant_number, Date arrival, Date departure, Long id_activity, Long id_user, boolean is_reserved) {
        this.adult_number = adult_number;
        this.enfant_number = enfant_number;
        this.arrival = arrival;
        this.departure = departure;
        this.id_activity = id_activity;
        this.id_user = id_user;
        this.is_reserved = is_reserved;
    }

    public Long getId_room() {
        return id_room;
    }

    public void setId_room(Long id_room) {
        this.id_room = id_room;
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

    public Long getId_activity() {
        return id_activity;
    }

    public void setId_activity(Long id_activity) {
        this.id_activity = id_activity;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public boolean getIs_reserved() {
        return is_reserved;
    }

    public void setIs_reserved(boolean is_reserved) {
        this.is_reserved = is_reserved;
    }
}
