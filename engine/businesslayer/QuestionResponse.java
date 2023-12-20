package engine.businesslayer;

import org.springframework.stereotype.Component;

@Component
public class QuestionResponse {
    boolean success;
    String feedback;

    public QuestionResponse() {
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
