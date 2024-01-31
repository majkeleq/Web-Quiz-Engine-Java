package engine.businesslayer.Quiz;

import org.springframework.stereotype.Component;

@Component
public class QuizResponse {
    boolean success;
    String feedback;

    public QuizResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
