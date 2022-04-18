package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.DTO.Message;
import com.bezkoder.springjwt.DTO.ReservationDto;
import com.bezkoder.springjwt.models.Reservation;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.ReservationRepo;
import com.bezkoder.springjwt.security.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    ReservationService reservationService;
    ReservationRepo reservationRepo;

    @GetMapping("/allReservation")
    public ResponseEntity<List<Reservation>> listReservation(){
        List<Reservation> reservations=reservationService.list();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PostMapping("/createReservation")
    public ResponseEntity<?> createReservation(@RequestBody ReservationDto reservationDto){
        // if (reservationRepo.findByroom_number(reservationDto.getRoom_number())) {
         //   return ResponseEntity
             //       .badRequest()
               //     .body(new MessageResponse("Error: room is already taken!"));
       // }
        Reservation reservation= new Reservation(reservationDto.getRoom_number(),
                reservationDto.getPerson_number(),
                reservationDto.getArrival(),
                reservationDto.getDeparture(),
                reservationDto.getActivity(),
                reservationDto.getUser() );
        reservationService.save(reservation);

        return new ResponseEntity(reservation,HttpStatus.OK);
    }

}
