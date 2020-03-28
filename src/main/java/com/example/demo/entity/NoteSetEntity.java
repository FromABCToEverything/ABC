package com.example.demo.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "note_set", schema = "test", catalog = "")
public class NoteSetEntity {
    private int setId;
    private String setName;
    private String creatorId;
    private String creatorName;
    private String avatarUrl;
    private Timestamp createdTime;
    private Timestamp lastEditTime;
    private int collectedTimes;
    private String personal;

    @Id
    @Column(name = "set_id", nullable = false)
    public int getSetId() {
        return setId;
    }

    public void setSetId(int setId) {
        this.setId = setId;
    }

    @Basic
    @Column(name = "set_name", nullable = false, length = 255)
    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
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
    @Column(name = "creator_name", nullable = false, length = 255)
    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
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
    @Column(name = "created_time", nullable = false)
    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Basic
    @Column(name = "last_edit_time", nullable = false)
    public Timestamp getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Timestamp lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    @Basic
    @Column(name = "collected_times", nullable = false)
    public int getCollectedTimes() {
        return collectedTimes;
    }

    public void setCollectedTimes(int collectedTimes) {
        this.collectedTimes = collectedTimes;
    }

    @Basic
    @Column(name = "personal", nullable = false, length = 1)
    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteSetEntity that = (NoteSetEntity) o;

        if (setId != that.setId) return false;
        if (collectedTimes != that.collectedTimes) return false;
        if (setName != null ? !setName.equals(that.setName) : that.setName != null) return false;
        if (creatorId != null ? !creatorId.equals(that.creatorId) : that.creatorId != null) return false;
        if (creatorName != null ? !creatorName.equals(that.creatorName) : that.creatorName != null) return false;
        if (avatarUrl != null ? !avatarUrl.equals(that.avatarUrl) : that.avatarUrl != null) return false;
        if (createdTime != null ? !createdTime.equals(that.createdTime) : that.createdTime != null) return false;
        if (lastEditTime != null ? !lastEditTime.equals(that.lastEditTime) : that.lastEditTime != null) return false;
        if (personal != null ? !personal.equals(that.personal) : that.personal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = setId;
        result = 31 * result + (setName != null ? setName.hashCode() : 0);
        result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
        result = 31 * result + (creatorName != null ? creatorName.hashCode() : 0);
        result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (lastEditTime != null ? lastEditTime.hashCode() : 0);
        result = 31 * result + collectedTimes;
        result = 31 * result + (personal != null ? personal.hashCode() : 0);
        return result;
    }
}
