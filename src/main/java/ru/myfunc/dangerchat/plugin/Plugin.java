package ru.myfunc.dangerchat.plugin;

import ru.myfunc.dangerchat.ChatRoom;
import ru.myfunc.dangerchat.model.*;

public abstract class Plugin {
    public abstract void userJoin(ChatRoom room, PluginEffect pluginEffect, User user);
    public abstract void userLeft(ChatRoom room, PluginEffect pluginEffect, User user);
    public abstract void messageSend(ChatRoom room, PluginEffect pluginEffect, Message message);
}
