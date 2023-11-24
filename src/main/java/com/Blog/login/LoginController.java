package com.Blog.login;

import com.Blog.user.User;
import com.Blog.user.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.Blog.session.Session;
import com.Blog.session.SessionRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.util.Optional;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    public LoginController(UserRepository userRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }
    @GetMapping("/show")
    public String home(HttpServletResponse response, Model model) {
        final String food = "Pizza";
        Cookie newCookie = new Cookie("food", food);
        response.addCookie(newCookie);

        return "/login/login";
    }
}
    @PostMapping("/login")
    public String login(HttpServletResponse response) {
        Optional<User> optionalUser = userRepository.findByUsernameAndPassword("hello", "password"); //später anpassen
        if (optionalUser.isPresent()) {
            Session session = new Session(optionalUser.get(), Instant.now().plusSeconds(7*24*60*60));
            sessionRepository.save(session);
            Cookie cookie = new Cookie("sessionId", session.getId());
            response.addCookie(cookie);
            // Login erfolgreich
            System.out.println("login erfolgreich");
            return "redirect:/";
        }
            // Login nicht erfolgreich
        System.out.println("login nicht erfolgreich");
        return "/login/login";
    }

}
