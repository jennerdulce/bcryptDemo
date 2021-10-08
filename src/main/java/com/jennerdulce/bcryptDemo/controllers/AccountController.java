package com.jennerdulce.bcryptDemo.controllers;

import com.jennerdulce.bcryptDemo.models.Account;
import com.jennerdulce.bcryptDemo.repositories.AccountRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/")
    public String getHomepage() {
        return "secretRecipe.html";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "/login.html";
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
        // Will hit the "/" route which renders the secretRecipe.html
        return new RedirectView("/");
    }

    @PostMapping("/signup")
    public RedirectView signUpUser(String username, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        Account newAccount = new Account(username, hashedPassword);
        accountRepository.save(newAccount);
        return new RedirectView("/login");
    }
}