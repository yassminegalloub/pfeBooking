package com.bezkoder.springjwt.security.services;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface RoomFileService {
    public void save(MultipartFile file, String name );
    public ResponseEntity<Resource> load(String filename) throws IOException;
}
