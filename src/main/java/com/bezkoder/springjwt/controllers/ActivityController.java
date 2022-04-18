package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.DTO.ActivityDto;
import com.bezkoder.springjwt.models.Activity;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.ActivityRepository;
import com.bezkoder.springjwt.security.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Activity")
public class ActivityController {
    @Autowired
    ActivityService activityService ;

    @PostMapping("/newActivity")
    public ResponseEntity<?> newActivity(@RequestBody ActivityDto activityDto){
        if(activityDto.getName()==null)
            return new ResponseEntity(new MessageResponse("Required name"), HttpStatus.BAD_REQUEST);
        Activity activity = new Activity(
                activityDto.getName(),
                activityDto.getActivity_schedule(),
                activityDto.getPromotion(),
                activityDto.getPicture(),
                activityDto.getAvailable());
        activityService.save(activity);
        return ResponseEntity.ok(new MessageResponse("activity registered successfully!"));
    }
    @GetMapping("/allActivity")
    public ResponseEntity<List<Activity>> list(){
        List<Activity> list =activityService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name="id")long id){
        if(!activityService.existsById(id))
            return new ResponseEntity(new MessageResponse("Not exist"), HttpStatus.NOT_FOUND);
        activityService.delete(id);
        return new ResponseEntity(new MessageResponse("Activity deleted successfully"), HttpStatus.OK);
    }


    @PostMapping("/update/{id}")
    public  ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ActivityDto activityDto){


        if (activityDto.getName()==null)
            return new ResponseEntity(new MessageResponse("enter le nom obligatoire"),HttpStatus.BAD_REQUEST);

        Activity activity = activityService.getOne((long) id).get();

        activity.setName(activityDto.getName());
        activity.setActivity_schedule(activityDto.getActivity_schedule());
        activity.setPromotion(activityDto.getPromotion());
        activity.setPicture(activityDto.getPicture());
        activity.setAvailable(activityDto.getAvailable());
        activityService.save(activity);
        return new ResponseEntity(new MessageResponse(" Activity updated succfuly"),HttpStatus.OK);

    }




}
