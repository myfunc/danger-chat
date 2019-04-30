package ru.myfunc.dangerchat.events;

import ru.myfunc.dangerchat.model.User;

public interface UserLeftListener {
    void handle(Object sender, User user);
}
