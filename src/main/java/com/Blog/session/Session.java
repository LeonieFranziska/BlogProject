package com.Blog.session;

import com.Blog.user.User;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity // diese Klasse/diese Objekte soll/en in der Datenbank landen --> Felder hier werden Spalten dort
@Table(name = "session") // festziehen wie die Tabelle heißen soll --> immer nutzen falls man refactoren möchte
public class Session {

    @Id // primary key
    @Column(name = "id") // in welche Spalte wird dieses Feld gemappt
    private String id = UUID.randomUUID().toString();

    @ManyToOne // Referenz auf die Usertabelle (viele Sessions für einen Nutzer)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "expires_at")
    private Instant expiresAt;


    public Session() { //default Konstruktor muss immer da sein
    }

    public Session(User user, Instant expiresAt) {
        this.user = user;
        this.expiresAt = expiresAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }
}
