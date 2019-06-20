package com.kketter.example.codeFellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.view.RedirectView;

import java.net.Authenticator;
import java.security.Principal;
import java.util.ArrayList;

@Controller
public class CodeFellowshipController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;


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
    //get whoever is logged in
    @GetMapping("/myprofile")
    public String getMyProfile(Model m, Principal p) {
        ApplicationUser me = applicationUserRepository.findByUsername(p.getName());
        System.out.println("name is" + p.getName());
        m.addAttribute("potato", me);
        return "myprofile";
    }
    //get user id to display
    @GetMapping("/user/{id}")
    public String getUserProfile(@PathVariable long id, Model m, Principal p) {
        ApplicationUser user = applicationUserRepository.findById(id).get();
        if (user.getId().equals(p.getName())){
            m.addAttribute("user", user);
            return "user";
    } else {
            throw new UserDoesNotBelongToYouException("That profile can not be referenced w/o sign in");
        }

    }

    @PostMapping("/registration")
    //parameters define from input form - registration.html
    public RedirectView createUser(String username, String password, String firstname, String lastname, String dateOfBirth, String bio) {
        //creating a newUser instance w/ hashed PW
        ApplicationUser newUser = new ApplicationUser(username, bCryptPasswordEncoder.encode(password), firstname, lastname, dateOfBirth, bio);
        //save new user to db using the applicationUserRepository as the middleman, creates sql queries
        applicationUserRepository.save(newUser);
        //logs in the new user automatically after creating - next 2 lines.  creates a token and does some other stuff
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //home for testing purposes - change this route to something that fits the problem domain better
        return new RedirectView("/myprofile");
        }

    }

//https://stackoverflow.com/questions/2066946/trigger-404-in-spring-mvc-controller
@ResponseStatus(value = HttpStatus.FORBIDDEN)
class UserDoesNotBelongToYouException extends RuntimeException {
    public UserDoesNotBelongToYouException(String potato) {
        super(potato);
    }
}
