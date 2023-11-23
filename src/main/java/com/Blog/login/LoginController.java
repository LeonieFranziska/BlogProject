package com.Blog.login;

import com.Blog.user.User;
import com.Blog.user.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import com.Blog.session.Session;
import com.Blog.session.SessionRepository;

import java.time.Instant;
import java.util.Optional;

@Controller
public class LoginController {

    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    public LoginController(UserRepository userRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    @PostMapping("/login")
    public String login(HttpServletResponse response) {
        Optional<User> optionalUser = userRepository.findByUsernameAndPassword("...", "..."); //später anpassen
        if (optionalUser.isPresent()) {
            Session session = new Session(optionalUser.get(), Instant.now().plusSeconds(7*24*60*60));
            sessionRepository.save(session);
            Cookie cookie = new Cookie("sessionId", session.getId());
            response.addCookie(cookie);
            // Login erfolgreich
            return "redirect:/";
        }
            // Login nicht erfolgreich
        return "login";
    }

}
