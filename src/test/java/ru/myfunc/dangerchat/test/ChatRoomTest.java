package ru.myfunc.dangerchat.test;

import org.junit.Test;
import ru.myfunc.dangerchat.ChatRoom;
import ru.myfunc.dangerchat.events.*;
import ru.myfunc.dangerchat.model.Message;
import ru.myfunc.dangerchat.model.User;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ChatRoomTest {
    @Test
    public void addUserTest() {
        var chatRoom = new ChatRoom();
        var userJoinListener = mock(UserJoinListener.class);
        chatRoom.onUserJoin(userJoinListener);
        chatRoom.addUser(new User(1, "test"));
        chatRoom.addUser(new User(2, "test"));
        chatRoom.addUser(new User(3, "test"));

        assertEquals(3, chatRoom.getUsers().size());
    }

    @Test
    public void removeUserTest() {
        var chatRoom = new ChatRoom();
        var userJoinListener = mock(UserJoinListener.class);
        chatRoom.onUserJoin(userJoinListener);
        chatRoom.addUser(new User(1, "test"));
        chatRoom.addUser(new User(2, "test"));
        chatRoom.addUser(new User(3, "test"));

        chatRoom.removeUser(new User(2, "testTwo"));
        chatRoom.removeUser(new User(1, "testTwo"));
        chatRoom.removeUser(new User(4, "testTwo"));

        assertEquals(1, chatRoom.getUsers().size());
    }

    @Test
    public void userJoinEventTest() {
        var chatRoom = new ChatRoom();
        var userJoinListener = mock(UserJoinListener.class);
        chatRoom.onUserJoin(userJoinListener);
        chatRoom.addUser(new User());
        chatRoom.addUser(new User());
        chatRoom.addUser(new User());

        verify(userJoinListener, times(3)).handle(any(), any());
    }

    @Test
    public void userLeftEventTest() {
        var chatRoom = new ChatRoom();
        var userLeftListener = mock(UserLeftListener.class);
        chatRoom.onUserLeft(userLeftListener);
        chatRoom.addUser(new User(1, "test"));
        chatRoom.addUser(new User(2, "test"));
        chatRoom.addUser(new User(3, "test"));

        chatRoom.removeUser(new User(2, "testTwo"));
        chatRoom.removeUser(new User(1, "testTwo"));
        chatRoom.removeUser(new User(4, "testTwo"));

        verify(userLeftListener, times(2)).handle(any(), any());
    }

    @Test
    public void messageSendTest() {
        var chatRoom = new ChatRoom();
        chatRoom.sendMessage(new Message());
        chatRoom.sendMessage(new Message());
        chatRoom.sendMessage(new Message());

        assertEquals(3, chatRoom.getMessages().size());
    }

    @Test
    public void messageSendsTest() {
        int a = 10;
        assertEquals(a, 10);
    }
}
