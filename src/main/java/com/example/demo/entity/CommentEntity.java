package com.example.demo.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment", schema = "test", catalog = "")
public class CommentEntity {
    private String type;
    private int toId;
    private String commentatorId;
    private String commentatorName;
    private String avatarUrl;
    private int score;
    private String content;
    private Timestamp lastEditTime;
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
    @Column(name = "to_id", nullable = false)
    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    @Basic
    @Column(name = "commentator_id", nullable = false, length = 255)
    public String getCommentatorId() {
        return commentatorId;
    }

    public void setCommentatorId(String commentatorId) {
        this.commentatorId = commentatorId;
    }

    @Basic
    @Column(name = "commentator_name", nullable = false, length = 255)
    public String getCommentatorName() {
        return commentatorName;
    }

    public void setCommentatorName(String commentatorName) {
        this.commentatorName = commentatorName;
    }

    @Basic
    @Column(name = "avatar_url", nullable = false, length = 255)
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Basic
    @Column(name = "score", nullable = false)
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 1024)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

        CommentEntity that = (CommentEntity) o;

        if (toId != that.toId) return false;
        if (score != that.score) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (commentatorId != null ? !commentatorId.equals(that.commentatorId) : that.commentatorId != null)
            return false;
        if (commentatorName != null ? !commentatorName.equals(that.commentatorName) : that.commentatorName != null)
            return false;
        if (avatarUrl != null ? !avatarUrl.equals(that.avatarUrl) : that.avatarUrl != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (lastEditTime != null ? !lastEditTime.equals(that.lastEditTime) : that.lastEditTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + toId;
        result = 31 * result + (commentatorId != null ? commentatorId.hashCode() : 0);
        result = 31 * result + (commentatorName != null ? commentatorName.hashCode() : 0);
        result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
        result = 31 * result + score;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (lastEditTime != null ? lastEditTime.hashCode() : 0);
        return result;
    }
}
