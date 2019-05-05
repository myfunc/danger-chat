package ru.myfunc.dangerchat.test;

import org.junit.Test;
import ru.myfunc.dangerchat.ChatRoom;
import ru.myfunc.dangerchat.events.*;
import ru.myfunc.dangerchat.model.Message;
import ru.myfunc.dangerchat.model.User;
import ru.myfunc.dangerchat.plugin.Plugin;
import ru.myfunc.dangerchat.plugin.PluginEffect;

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
    public void messageSendEventTest() {
        var chatRoom = new ChatRoom();
        var messageSendListener = mock(MessageSendListener.class);
        chatRoom.onMessageSend(messageSendListener);
        chatRoom.sendMessage(new Message());
        chatRoom.sendMessage(new Message());
        chatRoom.sendMessage(new Message());

        verify(messageSendListener, times(3)).handle(any(), any());
    }

    @Test
    public void pluginHandleSendMessageTest() {
        var chatRoom = new ChatRoom();
        var pluginOne = mock(Plugin.class);
        var pluginTwo = mock(Plugin.class);
        chatRoom.addPlugin(pluginOne);
        chatRoom.sendMessage(new Message());
        chatRoom.addPlugin(pluginTwo);

        verify(pluginOne, times(1)).messageSend(any(), any(), any());
        verify(pluginTwo, times(0)).messageSend(any(), any(), any());
    }

    @Test
    public void pluginSideEffectSendMessageTest() {
        var chatRoom = new ChatRoom();
        var textPlugin = new Plugin() {
            @Override
            public void messageSend(ChatRoom room, PluginEffect pluginEffect, Message message) {
                message.setText(message.getText() + "+");
            }
        };
        chatRoom.addPlugin(textPlugin);
        var testMessage = new Message();
        testMessage.setText("test");
        chatRoom.sendMessage(testMessage);

        String textOfLast = chatRoom.getMessages().getLast().getText();
        assertEquals("test+", textOfLast);
    }

    @Test
    public void pluginPreventEffectSendMessageTest() {
        var chatRoom = new ChatRoom();
        var textPlugin = new Plugin() {
            private int count = 0;
            @Override
            public void messageSend(ChatRoom room, PluginEffect pluginEffect, Message message) {
                pluginEffect.setPreventAction(count++ < 2);
            }
        };
        chatRoom.addPlugin(textPlugin);
        chatRoom.sendMessage(new Message());
        chatRoom.sendMessage(new Message());
        chatRoom.sendMessage(new Message());
        chatRoom.sendMessage(new Message());

        assertEquals(2, chatRoom.getMessages().size());
    }
}
