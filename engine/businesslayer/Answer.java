package engine.businesslayer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Answer {
    @JsonProperty("answer")
    List<Integer> answers;

    public Answer() {

    }
    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }
}
