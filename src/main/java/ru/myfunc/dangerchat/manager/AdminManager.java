package ru.myfunc.dangerchat.manager;

import ru.myfunc.dangerchat.*;

public class AdminManager {
    private ChatDomain chatDomain;

    public AdminManager(ChatDomain chatDomain) {
        this.chatDomain = chatDomain;
    }

    public void createRoom() {
        ChatRoom newRoom = new ChatRoom();
        newRoom.setId();
        chatDomain.addRoom();
    }

    public void deleteRoom() {

    }
}
