package com.Blog.registration;

import com.Blog.user.User;
import com.Blog.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/register")
    public String registerForm(Model model) {
        User theUser = new User();

        model.addAttribute("user", theUser);

        return "/registration/register-form";
    }

    @PostMapping(value = "/store")
    public String store(@ModelAttribute("user") User theUser, BindingResult result) {
        if (result.hasErrors()) {
            return "/registration/register-form";
        }

        userService.save(theUser);

        return "redirect:/user/show";
    }


}
