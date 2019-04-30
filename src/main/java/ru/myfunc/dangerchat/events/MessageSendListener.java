package ru.myfunc.dangerchat.events;

import ru.myfunc.dangerchat.model.Message;

public interface MessageSendListener {
    void handle(Object sender, Message message);
}
