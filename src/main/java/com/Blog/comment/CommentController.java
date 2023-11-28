package com.Blog.comment;

import com.Blog.post.Post;
import com.Blog.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/comments")
public class CommentController {

    private CommentRepository commentService;

    public CommentController(CommentRepository commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "/show")
    public String show(Model model) {


        List<Comment> theComments = commentService.findAll();


        model.addAttribute("commentList", theComments);

        return "/comments/comments";
    }

    @GetMapping(value = "/new")
    public String postForm(Model model) {
        Comment theComment = new Comment();

        model.addAttribute("comment", theComment);

        return "/comments/comments-form";
    }

    @PostMapping(value = "/store")
    public String store(@ModelAttribute("sessionUser") User user, @ModelAttribute("comment") Comment theComment, BindingResult result) {
        if (result.hasErrors()) {
            return "/comments/comments-form";
        }
        // Hier eine if user ist Admin abfrage!
        theComment.setUser(user);
        commentService.save(theComment);

        return "redirect:/posts/show";
    }
}
