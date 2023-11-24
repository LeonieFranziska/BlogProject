package com.Blog.user;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Integer userId);

    void save(User theUser);

    void deleteById(Integer userId);

}
