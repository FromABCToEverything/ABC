package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "session", schema = "test", catalog = "")
public class SessionEntity {
    private String openId;
    private String sessionId;
    @Id
    @Basic
    @Column(name = "open_id", nullable = false, length = 255)
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Basic
    @Column(name = "session_id", nullable = false, length = 255)
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionEntity that = (SessionEntity) o;

        if (openId != null ? !openId.equals(that.openId) : that.openId != null) return false;
        if (sessionId != null ? !sessionId.equals(that.sessionId) : that.sessionId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = openId != null ? openId.hashCode() : 0;
        result = 31 * result + (sessionId != null ? sessionId.hashCode() : 0);
        return result;
    }
}
