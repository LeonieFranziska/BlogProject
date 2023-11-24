package com.Blog.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        List<User> theUser = new ArrayList<>();
        theUser.add(new User(1,"hampel","password"));
        theUser.add(new User(2,"admin","s3cr3t"));
//                userService.findAll();

        model.addAttribute("userList", theUser);

        return "/user/user";
    }


}
