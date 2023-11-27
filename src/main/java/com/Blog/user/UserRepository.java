package com.Blog.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> { //von hibernate implementiert/bereitgestellt,
    // dort sind schon viele Methoden die man einfach out of the box nutzen kann
    Optional<User> findByUsernameAndPassword(String username, String password);
}
