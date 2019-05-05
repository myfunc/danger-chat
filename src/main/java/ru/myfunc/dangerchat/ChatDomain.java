package ru.myfunc.dangerchat;

import ru.myfunc.dangerchat.events.*;
import ru.myfunc.dangerchat.model.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ChatDomain {
    private LinkedList<ChatRoom> rooms = new LinkedList<>();
    private LinkedList<User> users = new LinkedList<>();
    private LinkedList<Group> groups = new LinkedList<>();

    private LinkedList<UserJoinListener> userJoinListeners = new LinkedList<>();
    private LinkedList<UserLeftListener> userLeftListeners = new LinkedList<>();
    private LinkedList<MessageSendListener> messageSendListeners = new LinkedList<>();

    private void subscribeToRoom(ChatRoom room){
        room.onUserJoin(this::emitUserJoin);
        room.onUserLeft(this::emitUserLeft);
        room.onMessageSend(this::emitMessageSend);
    }

    private void unsubscribeToRoom(ChatRoom room){
        room.offUserJoin(this::emitUserJoin);
        room.offUserLeft(this::emitUserLeft);
        room.offMessageSend(this::emitMessageSend);
    }

    public void onUserJoin(UserJoinListener handler){
        if (handler != null) {
            userJoinListeners.add(handler);
        }
    }

    public void onUserLeft(UserLeftListener handler){
        if (handler != null) {
            userLeftListeners.add(handler);
        }
    }

    public void onMessageSend(MessageSendListener handler){
        if (handler != null) {
            messageSendListeners.add(handler);
        }
    }

    public void offUserJoin(UserJoinListener handler){
        userJoinListeners.remove(handler);
    }

    public void offUserLeft(UserLeftListener handler){
        userLeftListeners.remove(handler);
    }

    public void offMessageSend(MessageSendListener handler){
        messageSendListeners.remove(handler);
    }

    private void emitUserJoin(Object sender, User user) {
        for (var listener : userJoinListeners) {
            listener.handle(sender, user);
        }
    }

    private void emitUserLeft(Object sender, User user){
        for (var listener : userLeftListeners) {
            listener.handle(sender, user);
        }
    }

    private void emitMessageSend(Object sender, Message message){
        for (var listener : messageSendListeners) {
            listener.handle(sender, message);
        }
    }

    public List<ChatRoom> getRooms() {
        return Collections.unmodifiableList(rooms);
    }

    public void addRoom(ChatRoom room) {
        rooms.add(room);
        subscribeToRoom(room);
    }

    public void removeRoom(ChatRoom room) {
        rooms.remove(room);
        unsubscribeToRoom(room);
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public List<Group> getGroups() {
        return Collections.unmodifiableList(groups);
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void removeGroup(Group group) {
        groups.remove(group);
    }

}
