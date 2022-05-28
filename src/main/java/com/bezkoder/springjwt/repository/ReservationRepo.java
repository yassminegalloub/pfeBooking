package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {

    //boolean findByroom_number(String room_number);

    boolean existsById(Long id);

   // List<Reservation> find
   // boolean existsByroom_number(String room_number);

}
