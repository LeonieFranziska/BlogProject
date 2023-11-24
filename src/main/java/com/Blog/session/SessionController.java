package com.Blog.session;

import com.Blog.user.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;
import java.util.Optional;

@Controller
public class SessionController { // Einstiegspunkt, um über das Internet zu meiner Applikation zu kommen

//    private SessionRepository sessionRepository;
//
//    public SessionController(SessionRepository sessionRepository) {
//        this.sessionRepository = sessionRepository;
//    }


   /* @GetMapping("/")
    public String home(HttpServletResponse response, Model model) {
        final String food = "Pizza";
        Cookie newCookie = new Cookie("food", food);
        response.addCookie(newCookie);

        return "home";
    }*/
}
