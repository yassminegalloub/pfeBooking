package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.DTO.ReservationDto;
import com.bezkoder.springjwt.models.*;
import com.bezkoder.springjwt.payload.request.SignupRequest;
import com.bezkoder.springjwt.repository.*;
import com.bezkoder.springjwt.security.services.ReservationService;
import com.bezkoder.springjwt.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    ReservationService reservationService;
    ReservationRepo reservationRepo;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ResRepository repository;
    RoleRepository roleRepository;



    @GetMapping("/allReservation")
    public List<Reservation> getReservation(){
        List<Reservation> reservation= reservationService.list();
        return  reservation ;
    }
    @GetMapping("/getReservation/{id}")
    public Optional<Reservation> getById(@PathVariable("id") Long id){
        Optional<Reservation> reservation= reservationService.getOne(id);
        return  reservation ;
    }
    @DeleteMapping("/DeleteReservation/{id}")
    public void deleteReservation(@PathVariable("id") Long id){
        reservationService.delete(id);
    }





    @PostMapping("/ReservationClient/{id_room}/{id_activity}/{id_user}")
    public Reservation ReservationClient(@PathVariable("id_room") Long id_room,@PathVariable("id_activity") Long id_activity,
                                               @PathVariable("id_user") Long id_user , @RequestBody ReservationDto reservationDto){
      //  Room room= roomRepository.findById(id_room).get();
      //  Activity activity=activityRepository.findById(id_activity).get();
        //User user=userRepository.findById(id_user).get();
        Reservation reservation= new Reservation();
        reservation.setRoom(id_room);
        reservation.setActivity(id_activity);
        reservation.setUser(id_user);
        reservation.setArrival(reservationDto.getArrival());
        reservation.setDeparture(reservationDto.getDeparture());
        reservation.setAdult_number(reservationDto.getAdult_number());
        reservation.setEnfant_number(reservationDto.getEnfant_number());
        return reservationService.save(reservation,id_room,id_activity,id_user);

    }

}
