package engine.businesslayer.Quiz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import engine.businesslayer.User.User;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quizzes")
public class Quiz {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotEmpty
    String title;
    @NotEmpty
    String text;
    @Size(min = 2)
    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    List<String> options;
    @JsonProperty(value = "answer", access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    List<Integer> answers;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    public Quiz() {

    }

    public Quiz(String title, String text, List<String> options, List<Integer> correctAnswer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answers = correctAnswer;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void afterValidation() {
        if (answers == null) {
            answers = new ArrayList<>();
        }
    }
}
