package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.DTO.ActivityDto;
import com.bezkoder.springjwt.DTO.Message;
import com.bezkoder.springjwt.models.Activity;
import com.bezkoder.springjwt.security.services.ActivityService;
import com.bezkoder.springjwt.security.services.FileImplService;
import com.bezkoder.springjwt.security.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
@RequestMapping("/Activity")
public class ActivityController {
    @Autowired
    ActivityService activityService ;
    @Autowired
    FileService fileService ;
    @Autowired
    FileImplService fileImplService ;


    @PostMapping(value ="/newActivity/{name}",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
        public ResponseEntity<?> newActivity(@RequestPart ActivityDto activityDto, @RequestPart MultipartFile file, @PathVariable("name") String name)  {
        String message = "";
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            Activity activity = new Activity(
                    activityDto.getName(),
                    activityDto.getActivity_schedule(),
                    activityDto.getPromotion(),
                    activityDto.getAvailable(),
                    fileName);
              activityService.save(activity);
            fileService.save(file, name);

        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        }
         message = "Activity: "+activityDto.getName()+ " and file registered successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new Message(message));
    }

    
    @GetMapping("/allActivity")
    public ResponseEntity<List<Activity>> list() throws IOException {
        List<Activity> list =activityService.list();
        //fileService.load(filename);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }



    @RequestMapping(value = "/load/{filename}" , method = RequestMethod.GET)
    public ResponseEntity<Resource> loadFile(@PathVariable("filename") String filename) throws IOException {
        return fileService.load(filename);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable long id) {
        Activity activity  = activityService.getFile(id);

        return (ResponseEntity<byte[]>) ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + activity.getFile() + "\"");

    }


}
