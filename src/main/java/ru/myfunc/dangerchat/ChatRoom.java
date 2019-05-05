package ru.myfunc.dangerchat;

import ru.myfunc.dangerchat.events.MessageSendListener;
import ru.myfunc.dangerchat.events.UserJoinListener;
import ru.myfunc.dangerchat.events.UserLeftListener;
import ru.myfunc.dangerchat.model.*;
import ru.myfunc.dangerchat.plugin.*;
import ru.myfunc.dangerchat.utils.Timer;

import java.util.LinkedList;
import java.util.Objects;

public class ChatRoom {
    private int id;
    private String name;

    private LinkedList<Message> messages = new LinkedList<Message>();
    private LinkedList<Plugin> plugins = new LinkedList<Plugin>();
    private LinkedList<User> users = new LinkedList<User>();

    private LinkedList<UserJoinListener> userJoinListeners = new LinkedList<>();
    private LinkedList<UserLeftListener> userLeftListeners = new LinkedList<>();
    private LinkedList<MessageSendListener> messageSendListeners = new LinkedList<>();

    private Timer pluginActionTimer = new Timer(()->tickTimer());

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

    public LinkedList<Message> getMessages() {
        return messages;
    }

    public LinkedList<Plugin> getPlugins() {
        return plugins;
    }

    public void addPlugin(Plugin plugin) {
        this.plugins.add(plugin);
    }

    public LinkedList<User> getUsers() {
        return users;
    }

    public void setupTimer(int milliseconds) {
        pluginActionTimer.setIntervalMs(milliseconds);
    }

    public void startTimer() {
        pluginActionTimer.start();
    }

    public void stopTimer() {
        pluginActionTimer.stop();
    }

    public void interruptTimer() {
        pluginActionTimer.interrupt();
    }

    public void tickTimer() {
        for (var plugin : plugins) {
            plugin.timer(this);
        }
    }

    public void addUser(User user){
        PluginEffect pluginEffect = new PluginEffect();
        for (var plugin : plugins) {
            plugin.userJoin(this, pluginEffect, user);
        }
        if (!pluginEffect.isPreventAction()) {
            users.add(user);
            emitUserJoin(user);
        }
    }

    public void removeUser(User user){
        PluginEffect pluginEffect = new PluginEffect();
        for (var plugin : plugins) {
            plugin.userLeft(this, pluginEffect, user);
        }
        if (!pluginEffect.isPreventAction()) {
            if (users.remove(user)) {
                emitUserLeft(user);
            }
        }
    }

    public void sendMessage(Message message){
        PluginEffect pluginEffect = new PluginEffect();
        for (var plugin : plugins) {
            plugin.messageSend(this, pluginEffect, message);
        }
        if (!pluginEffect.isPreventAction()) {
            messages.add(message);
            emitMessageSend(message);
        }
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

    private void emitUserJoin(User user) {
        for (var listener : userJoinListeners) {
            listener.handle(this, user);
        }
    }

    private void emitUserLeft(User user){
        for (var listener : userLeftListeners) {
            listener.handle(this, user);
        }
    }

    private void emitMessageSend(Message message){
        for (var listener : messageSendListeners) {
            listener.handle(this, message);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatRoom room = (ChatRoom) o;
        return getId() == room.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
