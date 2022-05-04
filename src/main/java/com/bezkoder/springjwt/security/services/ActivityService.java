package com.bezkoder.springjwt.security.services;


import com.bezkoder.springjwt.models.Activity;
import com.bezkoder.springjwt.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
        return activityRepository.findAll();
    }

    public Optional<Activity> getOne(Long id){
        return activityRepository.findById(id);
    }

    public void save(Activity activity){
        activityRepository.save(activity);
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

}
