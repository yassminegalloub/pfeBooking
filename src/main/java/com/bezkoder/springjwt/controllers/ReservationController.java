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
        Room room= roomRepository.findById(id_room).get();
        room.setStatus(true);
        roomRepository.save(room);
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
        reservation.setIs_reserved(false);
        return reservationService.save(reservation,id_room,id_activity,id_user);

    }
    @PutMapping("/UpdateReservation/{id}")
    public Reservation updateReservation(@PathVariable("id") Long id , @RequestBody ReservationDto reservationDto){
        Reservation reservation= reservationService.getOne(id).get();
        reservation.setRoom(reservationDto.getId_room());
        reservation.setActivity(reservationDto.getId_activity());
        reservation.setUser(reservationDto.getId_user());
        reservation.setArrival(reservationDto.getArrival());
        reservation.setDeparture(reservationDto.getDeparture());
        reservation.setAdult_number(reservationDto.getAdult_number());
        reservation.setEnfant_number(reservationDto.getEnfant_number());
        return reservationService.saveUpdate(reservation);

    }

    @PutMapping("/confirmerButton/{id}")
    public Reservation confirmerButton(@PathVariable("id") Long id){
        //Reservation reservation = reservationRepo.findById(id).get();
Reservation reservation= reservationService.getOne(id).get();
        reservation.setIs_reserved(true);
        return  reservationService.updateIsReserved(reservation);


    }



    @PostMapping("/ReservationAdmin/{id_room}/{id_activity}/{id_user}")
    public Reservation ReservationAdmin(@PathVariable("id_room") Long id_room,@PathVariable("id_activity") Long id_activity,
                                         @PathVariable("id_user") Long id_user , @RequestBody ReservationDto reservationDto){
        Room room= roomRepository.findById(id_room).get();
        room.setStatus(true);
        roomRepository.save(room);

        //  Activity activity=activityRepository.findById(id_activity).get();
        User user=userRepository.findById(id_user).get();
        Reservation reservation= new Reservation();
        reservation.setRoom(id_room);
        reservation.setActivity(id_activity);
        reservation.setUser(id_user);
        reservation.setArrival(reservationDto.getArrival());
        reservation.setDeparture(reservationDto.getDeparture());
        reservation.setAdult_number(reservationDto.getAdult_number());
        reservation.setEnfant_number(reservationDto.getEnfant_number());
        reservation.setIs_reserved(true);
        return reservationService.save(reservation,id_room,id_activity,id_user);

    }

    @PostMapping("/ReservationAdmin2/{id_room}/{id_activity}")
    public Reservation ReservationAdmin2(@PathVariable("id_room") Long id_room,@PathVariable("id_activity") Long id_activity,
                                       @RequestBody ReservationDto reservationDto , SignupRequest signupRequest){


        //  Activity activity=activityRepository.findById(id_activity).get();
       // User user=userRepository.findById(id_user).get();
        User user = new User();
        user.setName(signupRequest.getName());
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
       //// Set<Role> roles = new HashSet<>();

       // Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
        //        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        //roles.add(userRole);
       // user.setRoles(roles);
    // userRepository.findById(user.getId()).get();
        userRepository.save(user);
        Room room= roomRepository.findById(id_room).get();
        room.setStatus(true);
        roomRepository.save(room);
        Reservation reservation= new Reservation();
        reservation.setRoom(id_room);
        reservation.setActivity(id_activity);
        reservation.setUser(user.getId());
        reservation.setArrival(reservationDto.getArrival());
        reservation.setDeparture(reservationDto.getDeparture());
        reservation.setAdult_number(reservationDto.getAdult_number());
        reservation.setEnfant_number(reservationDto.getEnfant_number());
        reservation.setIs_reserved(true);
        return reservationService.saveRes(reservation,id_room,id_activity);

    }
    @GetMapping("/nbrReservation")
    public long nbrReservation(){
        return reservationService.nbrReservation() ;
    }

}
