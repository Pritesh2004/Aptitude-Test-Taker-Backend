package com.aptitude.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.aptitude.dto.VerifyEmailDto;
import com.aptitude.entity.Role;
import com.aptitude.entity.User;
import com.aptitude.entity.UserRole;
import com.aptitude.service.MailSenderService;
import com.aptitude.service.implementations.UserServiceImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MailSenderService mailService;

    @PostMapping("/")
    public ResponseEntity<User> registerUser(@RequestBody User newUser) throws Exception {
        newUser.setPassword(this.bCryptPasswordEncoder.encode(newUser.getPassword()));

        Set<UserRole> roles = new HashSet<>();
        Role role = new Role();
        role.setRoleId(20L);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(newUser);

        roles.add(userRole);

        User registeredUser = this.userService.createUser(newUser, roles);

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
        User user = this.userService.getUser(username);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(@RequestBody VerifyEmailDto mailDto) {
        mailService.sendEmail(mailDto.getEmail(), "Verify Your Email", "Otp for Email Verification is " + mailDto.getOtp());
        return ResponseEntity.ok("OTP Sent successfully.");
    }
}
