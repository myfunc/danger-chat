package ru.myfunc.dangerchat.test;

import org.junit.Test;
import ru.myfunc.dangerchat.ChatDomain;
import ru.myfunc.dangerchat.ChatRoom;
import ru.myfunc.dangerchat.events.*;
import ru.myfunc.dangerchat.model.*;

import static org.mockito.Mockito.*;

public class ChatDomainTest {
    @Test
    public void subscribeTests() {
        ChatDomain manager = spy(new ChatDomain());
        ChatRoom room1 = new ChatRoom();
        ChatRoom room2 = new ChatRoom();
        ChatRoom room3 = new ChatRoom();
        manager.addRoom(room1);
        manager.addRoom(room2);
        manager.addRoom(room3);

        MessageSendListener sendListener = mock(MessageSendListener.class);
        manager.onMessageSend(sendListener);

        room1.sendMessage(new Message());
        room2.sendMessage(new Message());
        room2.sendMessage(new Message());
        room3.sendMessage(new Message());

        verify(sendListener, times(4)).handle(any(),any());
    }
}
