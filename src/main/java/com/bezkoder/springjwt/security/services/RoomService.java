package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Activity;
import com.bezkoder.springjwt.models.Room;
import com.bezkoder.springjwt.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService implements RoomFileService {

    private final Path root = Paths.get("RoomsFile/");

    @Autowired
    RoomRepository roomRepository;
    public List<Room> list(){
        return roomRepository.findAll();
    }

    public Optional<Room> getOne(Long id){
        return roomRepository.findById(id);
    }

    public void save(Room room){
        roomRepository.save(room);
    }

    public void delete(Long id){
        roomRepository.deleteById(id);
    }

    public boolean existsById (Long id){  return roomRepository.existsById(id); }


    @Override
    public void save(MultipartFile file , String name) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(name));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Resource> load(String filename) throws IOException {
        Path filePath = root.toAbsolutePath().normalize().resolve(filename) ;
        Resource resource = new UrlResource(filePath.toUri()) ;
        HttpHeaders httpHeaders = new HttpHeaders() ;
        httpHeaders.add("File-Name" , filename);
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION , "attachment;File-Name" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    }
}
