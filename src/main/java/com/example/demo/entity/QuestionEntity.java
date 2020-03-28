package com.example.demo.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "question", schema = "test", catalog = "")
public class QuestionEntity {
    private int questionId;
    private String creatorId;
    private String type;
    private int pointId;
    private String stem;
    private String choices;
    private String answer;
    private String explanation;
    private Timestamp lastEditTime;

    @Id
    @Column(name = "question_id", nullable = false)
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "creator_id", nullable = false, length = 255)
    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 1)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "point_id", nullable = false)
    public int getPointId() {
        return pointId;
    }

    public void setPointId(int pointId) {
        this.pointId = pointId;
    }

    @Basic
    @Column(name = "stem", nullable = false, length = 511)
    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    @Basic
    @Column(name = "choices", nullable = false, length = 511)
    public String getChoices() {
        return choices;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    @Basic
    @Column(name = "answer", nullable = false, length = 1)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "explanation", nullable = false, length = 511)
    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    @Basic
    @Column(name = "last_edit_time", nullable = false)
    public Timestamp getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Timestamp lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionEntity that = (QuestionEntity) o;

        if (questionId != that.questionId) return false;
        if (pointId != that.pointId) return false;
        if (creatorId != null ? !creatorId.equals(that.creatorId) : that.creatorId != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (stem != null ? !stem.equals(that.stem) : that.stem != null) return false;
        if (choices != null ? !choices.equals(that.choices) : that.choices != null) return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;
        if (explanation != null ? !explanation.equals(that.explanation) : that.explanation != null) return false;
        if (lastEditTime != null ? !lastEditTime.equals(that.lastEditTime) : that.lastEditTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = questionId;
        result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + pointId;
        result = 31 * result + (stem != null ? stem.hashCode() : 0);
        result = 31 * result + (choices != null ? choices.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (explanation != null ? explanation.hashCode() : 0);
        result = 31 * result + (lastEditTime != null ? lastEditTime.hashCode() : 0);
        return result;
    }
}
