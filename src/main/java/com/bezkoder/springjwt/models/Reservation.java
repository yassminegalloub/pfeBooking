package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(	name = "reservation",
        uniqueConstraints =
                {@UniqueConstraint(columnNames = "Room_Number")})
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String room_number;

    private String person_number;

    private Date arrival;

    private Date departure ;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private User user;



    public Reservation() {
    }

    public Reservation(String room_number, String person_number, Date arrival, Date departure, Activity activity, User user) {
        this.room_number = room_number;
        this.person_number = person_number;
        this.arrival = arrival;
        this.departure = departure;
        this.activity= activity;
        this.user= user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public User getUser() { return user;}

    public void setUser(User user) { this.user = user;}
}
