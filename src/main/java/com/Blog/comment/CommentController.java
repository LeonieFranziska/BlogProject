package com.Blog.comment;

import com.Blog.post.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
