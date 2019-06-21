package com.kketter.example.codeFellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class PostController {
    @Autowired
    PostRepository postRepository;
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/")
    public String getRoot(Principal p, Model m) {
        if(p != null) {
            ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
            m.addAttribute("loggedInUser", currentUser);
            m.addAttribute("hasNoPosts", currentUser.posts.isEmpty());
        }
        return "home";
    }
    //when looking to add a post this is the route
    @GetMapping("/postForm")
    public String getNewPost(Principal p, Model m){
        m.addAttribute("principal", p);
        return "postForm";
    }

    @PostMapping("/postForm")
    public RedirectView addPost(String body, Principal p){
        Post post = new Post();
        post.body = body;
        post.creator = applicationUserRepository.findByUsername(p.getName());
        postRepository.save(post);
        //when a post is created redirected back to myprofile to view
        return new RedirectView("/myprofile");
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    class PostDoesNotBelongToYourException extends RuntimeException {
        public PostDoesNotBelongToYourException(String s){
            super(s);
        }
    }

}
