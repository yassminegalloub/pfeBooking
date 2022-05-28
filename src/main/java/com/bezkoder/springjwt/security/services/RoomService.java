package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.controllers.RoomController;
import com.bezkoder.springjwt.models.Room;
import com.bezkoder.springjwt.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class RoomService  {

    @Autowired
    RoomRepository roomRepository;
    //public List<Room> list(){return roomRepository.findAll();}
    public List<Room> list(){
        List<Room> roomList = roomRepository.findAll();
        String url;
        for(Room room:roomList){

            if(room.getFile()!=null ) {
                url = MvcUriComponentsBuilder
                        .fromMethodName(RoomController.class, "getFile", room.getFile()).build().toString();

                room.setFileURL(url);
            }
        }
        return roomList;
    }

    public Optional<Room> getOne(Long id){
        return roomRepository.findById(id);
    }

    public Room save(Room room){
        roomRepository.save(room);
        return room;
    }

    public void delete(Long id){
        roomRepository.deleteById(id);
    }

    public boolean existsById (Long id){  return roomRepository.existsById(id); }


    public Room getFile(Long id) {
        return roomRepository.findById(id).get();
    }

    public Stream<Room> getAllFiles() {
        return roomRepository.findAll().stream();
    }

}
