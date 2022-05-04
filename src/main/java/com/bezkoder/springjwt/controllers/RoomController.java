package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.DTO.ActivityDto;
import com.bezkoder.springjwt.DTO.Message;
import com.bezkoder.springjwt.DTO.RoomDto;
import com.bezkoder.springjwt.models.Activity;
import com.bezkoder.springjwt.models.Room;
import com.bezkoder.springjwt.security.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;
    @Autowired
    RoomFileService roomFileService;


    @PostMapping(value ="/addRoom/{name}",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> addRoom(@RequestPart RoomDto roomDto, @RequestPart MultipartFile file , @PathVariable("name") String name)  {
        String message = "";
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            Room room = new Room(
                    roomDto.getDetails(),
                    roomDto.getName_room(),
                    roomDto.getPrice(),
                    fileName);
            roomService.save(room);
            roomFileService.save(file, name);

        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        }
        message = "Room added  successfully and file : " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new Message(message));
    }

    @GetMapping("/allRoom")
    public ResponseEntity<List<Room>> list() throws IOException {
        List<Room> list =roomService.list();
        //roomService.load(filename);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/load/{filename}" , method = RequestMethod.GET)
    public ResponseEntity<Resource> loadFile(@PathVariable("filename") String filename) throws IOException {
        return roomService.load(filename);
    }


}
