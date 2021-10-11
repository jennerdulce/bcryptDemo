package com.jennerdulce.bcryptDemo.controllers;

import com.jennerdulce.bcryptDemo.models.Account;
import com.jennerdulce.bcryptDemo.models.Post;
import com.jennerdulce.bcryptDemo.repositories.AccountRepository;
import com.jennerdulce.bcryptDemo.repositories.PostRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    AccountRepository accountRepository;
    PostRepository postRepository;

    @GetMapping("/")
    public String getHomepage() {
        return "/posts.html";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "/login.html";
    }

    @GetMapping("/user/{userName}")
    public String userProfile(Model m, @PathVariable String userName) {
        Account accountFromDb = accountRepository.findByUsername(userName);
        m.addAttribute("account", accountFromDb);
        return "/posts.html";
    }

    @PostMapping("/login")
    public RedirectView addPost(String username, String password) {
        Account accountFromDb = accountRepository.findByUsername(username);
        if ((accountFromDb == null) || (!BCrypt.checkpw(password, accountFromDb.password))) {
            return new RedirectView("/login");
        }
        String route = "/user/" + username;
        // Will hit the "/" route which renders the posts.html
        return new RedirectView(route);
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "/signup.html";
    }

    // POSTS typically have a RedirectView
    @PostMapping("/login")
    public RedirectView logInUser(String username, String password) {
        Account accountFromDb = accountRepository.findByUsername(username);
        if ((accountFromDb == null) || (!BCrypt.checkpw(password, accountFromDb.password))) {
            return new RedirectView("/login");
        }
        String route = "/user/" + username;
        // Will hit the "/" route which renders the posts.html
        return new RedirectView(route);
    }

    @PostMapping("/signup")
    public RedirectView signUpUser(String username, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        Account newAccount = new Account(username, hashedPassword);
        accountRepository.save(newAccount);
        return new RedirectView("/login");
    }

    @PostMapping("/create-post")
    public RedirectView createPost(String username, String content) {
        Account currentAccount = accountRepository.findByUsername(username);
        Post newPost = new Post(content);
        List<Post> posts = currentAccount.getUsersPosts();
        if(posts == null){
            posts = new ArrayList<>();
        }
        posts.add(newPost);
        accountRepository.save(currentAccount);
        postRepository.save(newPost);
        return new RedirectView("/login");
    }
}