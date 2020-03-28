package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "score", schema = "test", catalog = "")
public class ScoreEntity {
    private String type;
    private int id;
    private int totalScore;
    private int scoredTimes;
    private Double avgScore;
    @Id
    @Basic
    @Column(name = "type", nullable = false, length = 1)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "total_score", nullable = false)
    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    @Basic
    @Column(name = "scored_times", nullable = false)
    public int getScoredTimes() {
        return scoredTimes;
    }

    public void setScoredTimes(int scoredTimes) {
        this.scoredTimes = scoredTimes;
    }

    @Basic
    @Column(name = "avg_score", nullable = true, precision = 0)
    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScoreEntity that = (ScoreEntity) o;

        if (id != that.id) return false;
        if (totalScore != that.totalScore) return false;
        if (scoredTimes != that.scoredTimes) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (avgScore != null ? !avgScore.equals(that.avgScore) : that.avgScore != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + totalScore;
        result = 31 * result + scoredTimes;
        result = 31 * result + (avgScore != null ? avgScore.hashCode() : 0);
        return result;
    }
}
