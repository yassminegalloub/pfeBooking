package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Reservation;
import com.bezkoder.springjwt.repository.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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

    public Reservation save(Reservation reservation, Long id_user, Long  id_activity, Long id_room) {
       return reservationRepo.save((reservation));

    }
    public Reservation saveRes(Reservation reservation, Long  id_activity, Long id_room) {
        return reservationRepo.save((reservation));

    }
    public Reservation saveUpdate(Reservation reservation) {
        return reservationRepo.save((reservation));

    }

    public Reservation updateIsReserved(Reservation reservation1) {
        return reservationRepo.save((reservation1));

    }

    public void delete(long id) {
        reservationRepo.deleteById(id);
    }
    public boolean existsById (Long id){return reservationRepo.existsById(id);}

    public Long nbrReservation(){
        return  reservationRepo.count();
    }



    //public boolean existsByroom_number(String room_number){ return reservationRepo.existsByroom_number(room_number); }



}
