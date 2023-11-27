package com.Blog.post;

import com.Blog.comment.Comment;
import com.Blog.user.User;
import com.Blog.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/posts")
public class PostController {

    private PostRepository postService;

    @Autowired
    public PostController(PostRepository postService) {
        this.postService = postService;
    }




    @GetMapping(value = "/show")
    public String show(Model model) {


        List<Post> thePost = postService.findAll();



        model.addAttribute("postList", thePost);

        return "/posts/posts";
    }
    @GetMapping(value = "/new")
    public String postForm(Model model) {
        Post thePost = new Post();

        model.addAttribute("post", thePost);

        return "/posts/posts-form";
    }

    @PostMapping(value = "/store")
    public String store(@ModelAttribute("post") Post thePost, BindingResult result) {
        if (result.hasErrors()) {
            return "/posts/posts-form";
        }
        // Hier eine if user ist Admin abfrage!

        postService.save(thePost);

        return "redirect:/posts/show";
    }

    @GetMapping(value = "/posts/{id}")
    public String postsForm(Model model, @PathVariable(name = "id") String id) {
        Post thePost = postService.findById(Integer.parseInt(id)).orElseThrow();

        model.addAttribute("post", thePost);

        return "/posts/posts-form";
    }
    @GetMapping(value = "/delete")
    public String deleteForm(Model model, @RequestParam(name = "postId") Integer id) {
        Post thePost = postService.findById(id).orElseThrow();

        model.addAttribute("post", thePost);

        return "/posts/delete-form";
    }
    @PostMapping(value = "/delete")
    public String delete(@ModelAttribute(name = "post") Post thePost) {
        postService.deleteById(thePost.getId());

        return "redirect:/posts/show";
    }






}
