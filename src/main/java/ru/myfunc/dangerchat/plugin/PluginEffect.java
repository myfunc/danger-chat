package ru.myfunc.dangerchat.plugin;

public class PluginEffect {
    private boolean isPreventAction = false;

    public boolean isPreventAction() {
        return isPreventAction;
    }

    public void setPreventAction(boolean preventAction) {
        isPreventAction = preventAction;
    }
}
