package engine.businesslayer;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
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
    List<String> options;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    List<Integer> answer;

    public Quiz() {
        id = ++count;
    }

    public Quiz(String title, String text, List<String> options, List<Integer> correctAnswer) {
        id = ++count;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = correctAnswer;
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

    public List<Integer> getAnswer() {
        return answer;
    }
}
