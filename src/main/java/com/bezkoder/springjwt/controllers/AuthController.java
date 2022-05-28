package com.bezkoder.springjwt.controllers;
import java.util.*;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.bezkoder.springjwt.repository.ActivityRepository;
import com.bezkoder.springjwt.security.services.ActivityService;
import com.bezkoder.springjwt.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.LoginRequest;
import com.bezkoder.springjwt.payload.request.SignupRequest;
import com.bezkoder.springjwt.payload.response.JwtResponse;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.jwt.JwtUtils;
import com.bezkoder.springjwt.security.services.UserDetailsImpl;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	UserService service ;
	@Autowired
	ActivityService activityService ;
	@Autowired
	ActivityRepository activityRepository;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getName(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				roles));
	}
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		// Create new user's account
		User user = new User( signUpRequest.getName(),signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);
						break;
					case "agent":
						Role agentRole = roleRepository.findByName(ERole.ROLE_AGENT)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(agentRole);
						break;
					case "reception":
						Role receptionRole = roleRepository.findByName(ERole.ROLE_RECEPTION)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(receptionRole);
						break;
					default:
						Role clientRole = roleRepository.findByName(ERole.ROLE_CLIENT)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(clientRole);
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	@PostMapping("/newReception")
	public ResponseEntity<?> newReception(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		// Create new user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();


		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_RECEPTION)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {

					case "agent":
						Role agentRole = roleRepository.findByName(ERole.ROLE_AGENT)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(agentRole);
						break;
					case "reception":
						Role receptionRole = roleRepository.findByName(ERole.ROLE_RECEPTION)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(receptionRole);
						break;

				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("Reception registered successfully!"));
	}

	@PostMapping("/newClient")
	public ResponseEntity<?> newClient(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		// Create new user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();


		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {

					case "agent":
						Role agentRole = roleRepository.findByName(ERole.ROLE_AGENT)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(agentRole);
						break;
					case "reception":
						Role receptionRole = roleRepository.findByName(ERole.ROLE_RECEPTION)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(receptionRole);
						break;

				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("Client registered successfully!"));
	}

	@GetMapping("/allUsers")
	public List<User> viewUsers(Model model, SignupRequest signUpRequest ){
		List<User> users = service.listAll();
		model.addAttribute("users", users);
		signUpRequest.getRole();
		return users ;
	}
	@GetMapping("/allReception")
	public ResponseEntity<?> viewReception(Model model, SignupRequest signUpRequest ){
		//if(User.getRoles().equals(roleRepository.findByName(ERole.ROLE_RECEPTION)) ){
			List<User> users = service.listAll();
			model.addAttribute("users", users);
			signUpRequest.getRole();

		//}
		return new ResponseEntity( HttpStatus.OK);

	}
    @GetMapping("/allRoles")
    public List<Role> viewRoles(Model model, SignupRequest signUpRequest ){
        List<Role> roles = service.listRoles();
        model.addAttribute("users", roles);
        signUpRequest.getRole();
        return  roles ;
    }

	@GetMapping("/details/{id}")
	public ResponseEntity<User> getById(@PathVariable("id") Long id){
		if (!service.existsById((long) id))
			return new ResponseEntity(new MessageResponse("not exist"), HttpStatus.NOT_FOUND);
		User user = service.getOne((long) id).get();
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@DeleteMapping("/DeleteUser/{id}")
	public void deleteUser(@PathVariable("id") Long idUser){
		service.delete(idUser);
	}

	@PostMapping("/editUser/{id}")
	public ResponseEntity<?> editUser(@PathVariable("id") Long id, @RequestBody SignupRequest signUpRequest){
		User user = service.getOne(id).get();
		user.setName(signUpRequest.getName());
		user.setUsername(signUpRequest.getUsername());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(encoder.encode(signUpRequest.getPassword()));
		service.save(user);
		return new ResponseEntity(user, HttpStatus.OK);
	}
	@PutMapping("/editPasswordUser/{id}")
	public ResponseEntity<?> editPasswordUser(@PathVariable("id") Long id, @RequestBody SignupRequest signUpRequest){
		Optional<User> user = service.getOne(id);
     if (user.isPresent()){
		 User user1=user.get();
		 user1.setPassword(signUpRequest.getPassword());
		 service.updatePassword(user1);
	 }

		return new ResponseEntity(user, HttpStatus.OK);
	}



}