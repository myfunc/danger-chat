package ru.myfunc.dangerchat.model;

import java.util.LinkedList;
import java.util.Objects;

public class Group {
    private int id;
    private String name;
    private LinkedList<String> tags;

    public Group() {
        this.tags = new LinkedList<String>();
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

    public LinkedList<String> getTags() {
        return tags;
    }

    public void setTags(LinkedList<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return getId() == group.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
