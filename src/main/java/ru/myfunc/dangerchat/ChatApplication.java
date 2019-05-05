package ru.myfunc.dangerchat;

import ru.myfunc.dangerchat.manager.*;

public class ChatApplication {
    private ChatDomain chatDomain;
    private AdminManager adminManager;
    private RoomManager roomManager;

    public ChatApplication() {
        chatDomain = new ChatDomain();
        adminManager = new AdminManager(chatDomain);
        roomManager = new RoomManager(chatDomain);
    }

    public void run() {

    }
}
