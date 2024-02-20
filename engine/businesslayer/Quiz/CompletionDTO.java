package engine.businesslayer.Quiz;


import java.util.Date;

public class CompletionDTO {
    private Long id;
    private Date completedAt;

    public CompletionDTO(Long id, Date completedAt) {
        this.id = id;
        this.completedAt = completedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }
}
