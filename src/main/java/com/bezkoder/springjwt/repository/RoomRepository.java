package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
