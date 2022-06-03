package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.DTO.ActivityDto;
import com.bezkoder.springjwt.DTO.Message;
import com.bezkoder.springjwt.DTO.RoomDTOs;
import com.bezkoder.springjwt.DTO.RoomDto;
import com.bezkoder.springjwt.models.Activity;
import com.bezkoder.springjwt.models.Room;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.SignupRequest;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.RoomRepository;
import com.bezkoder.springjwt.security.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;
    @Autowired(required = true)
    RoomFileService roomFileService;
    @Autowired
    RoomFileImplService roomFileImplService;
    @Autowired
    RoomRepository roomRepository ;



    @RequestMapping(value = "/addRoom", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public Room addRoom(@RequestParam("rooms") String rooms, @RequestParam("file") MultipartFile file ) throws IOException {
        System.out.println(rooms);
            RoomDTOs roomDto = new ObjectMapper().readValue(rooms, RoomDTOs.class);
            System.out.println("***************************");
            System.out.println(roomDto.getName());
            System.out.println(roomDto.getDetails());
            System.out.println(roomDto.getPrice());
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            Room room = new Room(
                    roomDto.getDetails(),
                    roomDto.getName(),
                    roomDto.getPrice(),
                    roomDto.getStatus(),
                    fileName);
        roomFileImplService.save(file);

            return roomService.save(room);



    }
    @PutMapping(value ="/editRoom/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> editRoom(@PathVariable("id") Long id, @RequestParam("rooms") String rooms, @RequestParam("file") MultipartFile file) throws IOException {
        RoomDTOs roomDto = new ObjectMapper().readValue(rooms, RoomDTOs.class);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Room room = roomService.getOne(id).get();
        //delete file
        roomFileImplService.delete(room.getFile());

        //update donnée
        room.setName_room(roomDto.getName());
        room.setDetails(roomDto.getDetails());
        room.setPrice(roomDto.getPrice());
        room.setFile(fileName);

        roomFileImplService.save(file);
        roomService.save(room);

        return new ResponseEntity(room, HttpStatus.OK);
    }
    @PostMapping("/editStatus/{id}")
    public  ResponseEntity<?> editRoom(@PathVariable("id") Long id,  @RequestBody RoomDTOs roomDTOs){
        Room room = roomService.getOne(id).get();
        room.setStatus(roomDTOs.getStatus());
        roomService.save(room);



        return new ResponseEntity(room, HttpStatus.OK);

    }

    @GetMapping("/allRoom")
    public ResponseEntity<List<Room>> list() throws IOException {

        List<Room> list =roomService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/DeleteRoom/{id}")
    public void deleteRoom(@PathVariable("id") Long idRoom ){
        Room room = roomService.getOne(idRoom).get();

        roomFileImplService.delete(room.getFile());
        roomService.delete(idRoom);

    }
    @GetMapping("/detailsRoom/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable("id") Long id){
        if (!roomService.existsById((long) id))
            return new ResponseEntity(new MessageResponse("not exist"), HttpStatus.NOT_FOUND);
        Room room = roomService.getOne(id).get();
        return new ResponseEntity(room, HttpStatus.OK);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = roomFileService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
   @GetMapping("/freeRoom")
    public List<Room> getfreeRoom(){
        return roomService.GetFreeRoom();
   }

    @GetMapping("/nbrRoomReserved")
    public Long nbrRoomReserved(){

        return roomService.nbrRoomReserved();

    }

    @GetMapping("/nbrRoomFree")
    public Long nbrRoomFree(){
        return roomService.nbrRoomFree();

    }
}
