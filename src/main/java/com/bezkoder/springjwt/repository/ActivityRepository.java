package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Optional<Activity> findById(Long id);

    Optional<Activity> findByName(String name);
}
