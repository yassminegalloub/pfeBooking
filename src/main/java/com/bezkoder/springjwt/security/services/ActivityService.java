package com.bezkoder.springjwt.security.services;


import com.bezkoder.springjwt.models.Activity;
import com.bezkoder.springjwt.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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


}
