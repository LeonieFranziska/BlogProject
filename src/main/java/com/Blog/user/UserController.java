package com.Blog.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private UserRepository userService;

    public UserController(UserRepository userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/show")
    public String show(Model model) {

        List<User> theUser    =  userService.findAll();

        model.addAttribute("userList", theUser);

        return "/user/user";
    }
    @GetMapping(value = "/promoteAdmin")
    public String promoteAdmin(Model model, @RequestParam(name = "userId") Integer id) {
        User theUser = userService.findById(id).orElseThrow();

        model.addAttribute("user", theUser);

        return "/user/user-admin";
    }
    @PostMapping(value = "/promoteAdmin")
    public String promoteAdmin(@ModelAttribute(name = "user") User theUser) {
        theUser.setAdmin(true);
        userService.save(theUser);


        return "redirect:/user/show";
    }

    @GetMapping(value = "/demoteAdmin")
    public String demoteAdmin(Model model, @RequestParam(name = "userId") Integer id) {
        User theUser = userService.findById(id).orElseThrow();

        model.addAttribute("user", theUser);

        return "/user/user-admin";
    }
    @PostMapping(value = "/demoteAdmin")
    public String demoteAdmin(@ModelAttribute(name = "user") User theUser) {
        theUser.setAdmin(false);
        userService.save(theUser);


        return "redirect:/user/show";
    }

}
