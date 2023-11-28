package com.Blog.comment;

import com.Blog.post.Post;
import com.Blog.post.PostRepository;
import com.Blog.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/comments")
public class CommentController {

    private PostRepository postService;
    private CommentRepository commentService;
    @Autowired
    public CommentController(PostRepository postService, CommentRepository commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping(value = "/show")
    public String show(Model model) {

        List<Comment> theComments = commentService.findAll();

        model.addAttribute("commentList", theComments);

        return "/comments/comments";
    }

    @GetMapping(value = "/new/{postId}")
    public String postForm(Model model, @PathVariable(name = "postId") Integer id) {
        Comment theComment = new Comment();

        model.addAttribute("comment", theComment);

        Post thePost = postService.findById(id).orElseThrow();

        model.addAttribute("post", thePost);

        return "/comments/comments-form";
    }

    @PostMapping(value = "/store/{postId}")
    public String store(@ModelAttribute("sessionUser") User user, @ModelAttribute("comment") Comment theComment, @PathVariable(name = "postId") Integer id, BindingResult result) {
        if (result.hasErrors()) {
            return "/comments/comments-form";
        }

        Post thePost = postService.findById(id).orElseThrow();
        // Hier eine if user ist Admin abfrage!
        theComment.setUser(user);
        theComment.setPost(thePost);
        commentService.save(theComment);

        return "redirect:/posts/show";
    }
}
