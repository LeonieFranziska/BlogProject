package com.Blog.post;

import com.Blog.comment.Comment;
import com.Blog.user.User;
import com.Blog.user.UserService;
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

    private PostRepository postService;

    public PostController(PostRepository postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/show")
    public String show(Model model) {
        List<Post> thePost = new ArrayList<>();
        thePost.add(new Post(1,
                "first Post",
                "balb bla balb bla balb bla balb bla balb bla balb bla ",
                LocalDate.now(),
                new User(2,"admin","s3cr3t"),
                List.of(new Comment())));
        thePost.add(new Post(2,
                "#2 Post",
                "balb bla balb bla balb bla balb bla balb bla balb bla ",
                LocalDate.now(),
                new User(2,"admin","s3cr3t"),
                List.of(new Comment())));

//                userService.findAll();

        model.addAttribute("postList", thePost);

        return "/posts/posts";
    }







}
