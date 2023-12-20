package engine.businesslayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;


public class Question {
    private static int count = 0;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    int id;
    String title;
    String text;
    String[] options;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    int correctAnswer;

    public Question() {
        id = ++count;
    }

    public Question(String title, String text, String[] options, int correctAnswer) {
        id = ++count;
        this.title = title;
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
