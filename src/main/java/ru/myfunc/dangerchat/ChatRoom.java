package ru.myfunc.dangerchat;

import ru.myfunc.dangerchat.events.MessageSendListener;
import ru.myfunc.dangerchat.events.UserJoinListener;
import ru.myfunc.dangerchat.events.UserLeftListener;
import ru.myfunc.dangerchat.model.*;
import ru.myfunc.dangerchat.plugin.Plugin;
import ru.myfunc.dangerchat.plugin.PluginEffect;

import java.util.HashSet;
import java.util.LinkedList;

public class ChatRoom {
    private LinkedList<Message> messages = new LinkedList<Message>();
    private LinkedList<Plugin> plugins = new LinkedList<Plugin>();
    private LinkedList<User> users = new LinkedList<User>();

    private HashSet<UserJoinListener> userJoinListeners = new HashSet<>();
    private HashSet<UserLeftListener> userLeftListeners = new HashSet<>();
    private HashSet<MessageSendListener> messageSendListeners = new HashSet<>();

    public LinkedList<Message> getMessages() {
        return messages;
    }

    public LinkedList<Plugin> getPlugins() {
        return plugins;
    }

    public LinkedList<User> getUsers() {
        return users;
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

    private void emitUserJoin(User user) {
        for (var listener : userJoinListeners) {
            listener.handle(this, user);
        }
    }

    public void emitUserLeft(User user){
        for (var listener : userLeftListeners) {
            listener.handle(this, user);
        }
    }

    public void emitMessageSend(Message message){
        for (var listener : messageSendListeners) {
            listener.handle(this, message);
        }
    }
}
