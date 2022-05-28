package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {
    List<Room> findByStatus( boolean status);
}
