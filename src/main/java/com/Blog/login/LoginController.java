package com.Blog.login;

import com.Blog.user.User;
import com.Blog.user.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping("/login")
    public String loginForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "/login/login";
    }
    //An HTML leeren Nutzer

    @PostMapping("/login/start")
    public String login(HttpServletResponse response, @ModelAttribute(name="user") User user) {
        Optional<User> optionalUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()); //später anpassen
        System.out.println(optionalUser);
        if (optionalUser.isPresent()) {
            Session session = new Session(optionalUser.get(), Instant.now().plusSeconds(7*24*60*60));
            sessionRepository.save(session); //Neues Feld in der DB
            Cookie cookie = new Cookie("sessionId", session.getId());
            cookie.setPath("/");
            response.addCookie(cookie);
            // Login erfolgreich
            return "redirect:/posts/show";
        }
            // Login nicht erfolgreich
        return "login";
    }

}
