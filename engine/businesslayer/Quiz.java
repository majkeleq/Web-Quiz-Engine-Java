package engine.businesslayer;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


public class Quiz {
    private static int count = 0;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    int id;
    @NotEmpty
    String title;
    @NotEmpty
    String text;
    @Size(min = 2)
    @NotNull
    List<String> options;
    @NotNull
    @JsonProperty(value = "answer", access = JsonProperty.Access.WRITE_ONLY)
    List<Integer> answers;

    public Quiz() {

    }

    public Quiz(String title, String text, List<String> options, List<Integer> correctAnswer) {
        id = ++count;
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

    public void setId() {
        id = ++count;
    }
}
