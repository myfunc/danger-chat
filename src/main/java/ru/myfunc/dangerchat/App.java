package ru.myfunc.dangerchat;

import ru.myfunc.dangerchat.plugin.Plugin;

public class App {
    public static void main(String[] args) throws Exception {
        ChatRoom room = new ChatRoom();
        room.addPlugin(new MessagePlugin("First"));
        room.addPlugin(new MessagePlugin("Second"));
        room.addPlugin(new MessagePlugin("Third"));

        room.setupTimer(2000);
        room.startTimer();

        Thread.sleep(5000);
        room.stopTimer();
    }
}

class MessagePlugin extends Plugin {
    private String name;

    public MessagePlugin(String name) {
        this.name = name;
    }

    @Override
    public void timer(ChatRoom room) {
        System.out.printf("Plugin %s tick\n", name);
    }
}