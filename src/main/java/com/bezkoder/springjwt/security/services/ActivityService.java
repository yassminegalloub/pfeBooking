package com.bezkoder.springjwt.security.services;


import com.bezkoder.springjwt.controllers.ActivityController;
import com.bezkoder.springjwt.models.Activity;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Transactional
public class ActivityService {
    @Autowired
    ActivityRepository activityRepository ;

    public List<Activity> list(){
        List<Activity> activityList = activityRepository.findAll();
        String url;
        for(Activity activity:activityList){

            if(activity.getFile()!=null ) {
                url = MvcUriComponentsBuilder
                        .fromMethodName(ActivityController.class, "getFile", activity.getFile()).build().toString();

                activity.setFileURL(url);
            }

        }
        return activityList;

    }

    public Optional<Activity> getOne(Long id){
        return activityRepository.findById(id);
    }

    public Activity updateAvailable(Activity activity1) {
        return activityRepository.save(activity1);
    }

    public Activity save(Activity activity){
        activityRepository.save(activity);
        return  activity;
    }

    public void delete(Long id){
        activityRepository.deleteById(id);
    }

    public boolean existsById (Long id){  return activityRepository.existsById(id); }

    public Activity store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Activity activity = new Activity(fileName);

        return activityRepository.save(activity);
    }

    public Activity getFile(Long id) {
        return activityRepository.findById(id).get();
    }

    public Stream<Activity> getAllFiles() {
        return activityRepository.findAll().stream();
    }
    public Long nbrActivity(){
        return  activityRepository.count();
    }
}
