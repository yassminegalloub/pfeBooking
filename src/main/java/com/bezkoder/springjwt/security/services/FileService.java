package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Activity;
import com.bezkoder.springjwt.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;
@Service
public interface FileService {
    public void save(MultipartFile file , String name);
    public ResponseEntity<Resource> load(String filename) throws IOException;
}
