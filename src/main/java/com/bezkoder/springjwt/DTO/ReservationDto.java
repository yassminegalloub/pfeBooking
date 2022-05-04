package com.bezkoder.springjwt.DTO;

import com.bezkoder.springjwt.models.Activity;
import com.bezkoder.springjwt.models.User;

import java.util.Date;

public class ReservationDto {
    private String room_number;

    private Long adult_number;

    private Long enfant_number ;

    private Date arrival;

    private Date departure ;

    private Activity activity;
     private User user ;





     //password=getOne()+

    public ReservationDto() {
    }

    public ReservationDto(String room_number, long adult_number, long enfant_number, Date arrival, Date departure, Activity activity, User user) {
        this.room_number = room_number;
        this.adult_number = adult_number;
        this.enfant_number = enfant_number;
        this.arrival = arrival;
        this.departure = departure;
        this.user= user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
