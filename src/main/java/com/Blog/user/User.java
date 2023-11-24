package com.Blog.user;

import com.Blog.comment.Comment;
import com.Blog.post.Post;
import com.Blog.session.Session;
import jakarta.persistence.*;

import java.util.List;
import java.util.concurrent.CompletionException;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    @Column(name = "posts")
    private List<Post> posts;
    @OneToMany(mappedBy = "user")
    @Column(name = "comments")
    private List<Comment> comments;

    public User() {

    }

    public User(int id, String username, String password, List<Post> posts, List<Comment> comments) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.posts = posts;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
