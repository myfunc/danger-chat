package ru.myfunc.dangerchat.events;

import ru.myfunc.dangerchat.model.User;

public interface UserJoinListener {
    void handle(Object sender, User user);
}
