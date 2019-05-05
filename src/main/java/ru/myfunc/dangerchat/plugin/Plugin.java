package ru.myfunc.dangerchat.plugin;

import ru.myfunc.dangerchat.ChatRoom;
import ru.myfunc.dangerchat.model.*;

public abstract class Plugin {
    public void userJoin(ChatRoom room, PluginEffect pluginEffect, User user) {}
    public void userLeft(ChatRoom room, PluginEffect pluginEffect, User user) {}
    public void messageSend(ChatRoom room, PluginEffect pluginEffect, Message message) {}
    public void timer(ChatRoom room) {}
}
