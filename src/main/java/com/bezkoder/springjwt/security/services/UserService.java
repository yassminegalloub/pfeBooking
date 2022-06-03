package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.Room;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository ;
    @Autowired
    private RoleRepository roleRepository ;

    public List<User> listAll(){ return  userRepository.findAll();}

    public List<User> listReception(){

        return userRepository.findAll(); }

    public List<Role> listRoles(){ return roleRepository.findAll(); }
    public void save(User user){ userRepository.save(user);}

    public void delete(Long id) { userRepository.deleteById(id);}

    public boolean existsById (Long id){
        return userRepository.existsById(id);
    }
    public  Optional<User> getOne(Long id) { return userRepository.findById(id);}

    public User updatePassword(User user1) {
        return userRepository.save(user1);
    }

    public Long nbrUser(){
        return  userRepository.count();
    }

}
