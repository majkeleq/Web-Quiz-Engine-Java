package engine.businesslayer.User;

import engine.businesslayer.Quiz.Completion;
import engine.businesslayer.Quiz.Quiz;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval=true)
    private List<Quiz> quizzes = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval=true)
    private List<Completion> completions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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