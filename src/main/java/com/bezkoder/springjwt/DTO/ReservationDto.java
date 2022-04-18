package com.bezkoder.springjwt.DTO;

import com.bezkoder.springjwt.models.Activity;
import com.bezkoder.springjwt.models.User;

import java.util.Date;

public class ReservationDto {
    private String room_number;

    private String person_number;

    private Date arrival;

    private Date departure ;

    private Activity activity;
     private User user ;





     //password=getOne()+

    public ReservationDto() {
    }

    public ReservationDto(String room_number, String person_number, Date arrival, Date departure, Activity activity, User user) {
        this.room_number = room_number;
        this.person_number = person_number;
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

    public String getPerson_number() {
        return person_number;
    }

    public void setPerson_number(String person_number) {
        this.person_number = person_number;
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
