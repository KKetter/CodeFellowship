package com.kketter.example.codeFellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CodeFellowshipController {

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }

    @GetMapping("/registration")
    public String getRegistration() {
        return "registration";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

//    An ApplicationUser should have a username, password ( hashed using BCrypt),
//    firstName, lastName, dateOfBirth, bio, and any other fields you think are useful.
    //repository?


//    The site should allow users to create an ApplicationUser on the “sign up” page.

//    Your Controller should have an @Autowired private PasswordEncoder passwordEncoder;
//    and use that to run passwordEncoder.encode(password) before saving the password into the new user.
//    @Autowired
//    private PasswordEncoder passwordEncoder(String input){
//        return passwordEncoder.encode(input);
//    }

}
