package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(	name = "reservation"
       )
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long room;

    private Long adult_number;

    private Long enfant_number ;

    private Date arrival;

    private Date departure ;

    private Long activity;

    private Long user;

    private boolean is_reserved ;



    public Reservation() {
    }

    public Reservation(Long room, Long adult_number, Long enfant_number, Date arrival, Date departure, Long activity, Long user, boolean is_reserved) {
        this.room = room;
        this.adult_number = adult_number;
        this.enfant_number = enfant_number;
        this.arrival = arrival;
        this.departure = departure;
        this.activity = activity;
        this.user = user;
        this.is_reserved= is_reserved ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoom() {
        return room;
    }

    public void setRoom(Long room) {
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

    public Long getActivity() {
        return activity;
    }

    public void setActivity(Long activity) {
        this.activity = activity;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public boolean getIs_reserved() {
        return is_reserved;
    }

    public void setIs_reserved(boolean is_reserved) {
        this.is_reserved = is_reserved;
    }
}
