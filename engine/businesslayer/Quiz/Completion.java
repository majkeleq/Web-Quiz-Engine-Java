package engine.businesslayer.Quiz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import engine.businesslayer.User.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "completions")
public class Completion {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JsonProperty(value = "id",access = JsonProperty.Access.READ_ONLY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Temporal(TemporalType.TIMESTAMP)
    private Date completedAt;

    public Completion() {
    }

    public Completion(User user, Quiz quiz) {
        this.user = user;
        this.quiz = quiz;
        this.completedAt = java.sql.Timestamp.valueOf(LocalDateTime.now());
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }
}
