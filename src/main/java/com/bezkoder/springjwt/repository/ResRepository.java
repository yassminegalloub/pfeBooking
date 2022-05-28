package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAll ();
}
