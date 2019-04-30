package ru.myfunc.dangerchat.model;

import java.util.Date;
import java.util.Objects;

public class Session {
    private int id;
    private Date started;
    private int liveSeconds;
    private String token;
    private User user;

    public Session(int id, Date started, int liveSeconds, String token, User user) {
        this.id = id;
        this.started = started;
        this.liveSeconds = liveSeconds;
        this.token = token;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public Date getStarted() {
        return started;
    }

    public int getLiveSeconds() {
        return liveSeconds;
    }

    public void setLiveSeconds(int liveSeconds) {
        this.liveSeconds = liveSeconds;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return getId() == session.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
