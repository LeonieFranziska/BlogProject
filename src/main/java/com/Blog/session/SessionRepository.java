package com.Blog.session;

import com.Blog.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, String> { //in den spitzen Klammern immer zuerst die
    // Entität die in die Datenbank soll und dann welcher Typ der primary key ist --> Spring weiß, dass er eine Bean erstellen soll
    // und nimmt uns die Arbeit ab, aus dem Interface eine Klasse zu erstellen und sie zu instanziieren und in den Controller zu injecten
    Optional<Session> findByIdAndExpiresAtAfter(String sessionId, Instant now);

    void deleteByUser(User user);
}
