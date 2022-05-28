package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.DTO.ActivityDto;
import com.bezkoder.springjwt.DTO.Message;
import com.bezkoder.springjwt.DTO.RoomDTOs;
import com.bezkoder.springjwt.models.Activity;
import com.bezkoder.springjwt.models.Room;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.security.services.ActivityService;
import com.bezkoder.springjwt.security.services.FileImplService;
import com.bezkoder.springjwt.security.services.FileService;
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

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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


    @PostMapping(value ="/newActivity",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
        public Activity newActivity(@RequestParam("activities") String activities, @RequestParam("file") MultipartFile file ) throws IOException {
        String message = "";

            ActivityDto activityDto = new ObjectMapper().readValue(activities, ActivityDto.class);

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());


            Activity activity = new Activity(
                    activityDto.getName(),
                    activityDto.getActivity_schedule(),
                    activityDto.getPromotion(),
                    activityDto.getAvailable(),
                    fileName);
        fileImplService.save(file);

        return activityService.save(activity);
    }
    @DeleteMapping("/DeleteActivity/{id}")
    public void deleteActivity(@PathVariable("id") Long id ){
        Activity activity = activityService.getOne(id).get();

        fileImplService.delete(activity.getFile());
        activityService.delete(id);

    }
    @PostMapping(value ="/editActivity/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> editRoom(@PathVariable("id") Long id, @RequestParam("activities") String activities, @RequestParam("file") MultipartFile file) throws IOException {
        ActivityDto activityDto = new ObjectMapper().readValue(activities, ActivityDto.class);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Activity activity = activityService.getOne(id).get();
        //delete file
        fileImplService.delete(activity.getFile());

        //update donnée
        activity.setName(activityDto.getName());
        activity.setActivity_schedule(activityDto.getActivity_schedule());
        activity.setAvailable(activityDto.getAvailable());
        activity.setPromotion(activityDto.getPromotion());
        activity.setFile(fileName);

        fileImplService.save(file);
        activityService.save(activity);

        return new ResponseEntity(activity, HttpStatus.OK);
    }
    @PostMapping("/editAvailable/{id}")
    public Activity editAvailable(@PathVariable("id") Long id, @RequestBody ActivityDto activityDto){
        Optional<Activity> activity = activityService.getOne(id);
        Activity activity1=activity.get();

        activity1.setAvailable(activityDto.getAvailable());

        return activityService.updateAvailable(activity1);
    }
    @GetMapping("/allActivity")
    public ResponseEntity<List<Activity>> list() throws IOException {
        List<Activity> list =activityService.list();
        //fileService.load(filename);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


}
