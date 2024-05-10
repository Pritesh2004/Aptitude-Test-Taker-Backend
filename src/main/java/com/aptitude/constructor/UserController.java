package com.aptitude.constructor;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aptitude.dto.VerifyEmailDto;
import com.aptitude.entity.Role;
import com.aptitude.entity.User;
import com.aptitude.entity.UserRole;
import com.aptitude.service.MailSenderService;
import com.aptitude.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	
	@Autowired
	private MailSenderService mailService;
	
	
	//To register a new User
	@PostMapping("/")
	public ResponseEntity<User> registerUser(@RequestBody User newuser) throws Exception{
		
		
		Set<UserRole> roles = new HashSet<>();
		
		Role role = new Role();
		role.setRoleId(40l);
		role.setRoleName("ADMIN");
		
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(newuser);
		
		roles.add(userRole);
		
		User registeredUser = this.userService.createUser(newuser, roles);
		
	    return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
		
	}
	
	
	 	@GetMapping("/{username}")
	    public User getUser(@PathVariable("username") String username) {
	        return this.userService.getUser(username);
	    }

	    //delete the user by id
	    @DeleteMapping("/{userId}")
	    public void deleteUser(@PathVariable("userId") Long userId) {
	        this.userService.deleteUser(userId);
	    }

	    
	    @PostMapping("/verify-otp")
	    public ResponseEntity<String> verifyOTP(@RequestBody VerifyEmailDto mailDto) {
		 
		 mailService.sendEmail(mailDto.getEmail(),"Verify Your Email", "Otp for Email Verification is "+mailDto.getOtp() );
		 return ResponseEntity.ok("OTP Sent successfully.");
	    }
	 

}
