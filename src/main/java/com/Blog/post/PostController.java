package com.Blog.post;

import com.Blog.comment.Comment;
import com.Blog.user.User;
import com.Blog.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/posts")
public class PostController {

    private PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/show")
    public String show(Model model) {


        List<Post> thePost = postService.findAll();



        model.addAttribute("postList", thePost);

        return "/posts/posts";
    }







}
