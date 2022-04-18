package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Reservation;
import com.bezkoder.springjwt.repository.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationService {
    @Autowired
    ReservationRepo reservationRepo;

    public List<Reservation> list() {
        return reservationRepo.findAll();
    }

    public Optional<Reservation> getOne(Long id) {
        return reservationRepo.findById(id);
    }

    public void save(Reservation reservation) {
        reservationRepo.save((reservation));
    }

    public void delete(long id) {
        reservationRepo.deleteById(id);
    }
    public boolean existsById (Long id){return reservationRepo.existsById(id);}

    //public boolean existsByroom_number(String room_number){ return reservationRepo.existsByroom_number(room_number); }



}
