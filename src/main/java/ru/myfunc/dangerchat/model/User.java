package ru.myfunc.dangerchat.model;

import ru.myfunc.dangerchat.ChatRoom;

import java.util.LinkedList;
import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String password;
    private boolean isAnonymous;

    private LinkedList<ChatRoom> rooms = new LinkedList<>();

    private LinkedList<String> tags = new LinkedList<String>();

    public User() { }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        isAnonymous = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LinkedList<String> getTags() {
        return tags;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    public LinkedList<ChatRoom> getRooms() {
        return rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
