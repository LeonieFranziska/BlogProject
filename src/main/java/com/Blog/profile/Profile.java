package com.Blog.profile;

import com.Blog.user.User;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "profile", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

  /*  @OneToOne(mappedBy = "profile", cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    private User user;
*/
}
