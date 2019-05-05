package ru.myfunc.dangerchat.utils;

public class Timer {
    private int intervalMs = 1000;
    private TimerTickAction action;
    private boolean isTimerActive = false;
    private Thread timerThread;

    public Timer(TimerTickAction action) {
        this.action = action;
    }

    public void start() {
        if (isTimerActive) {
            return;
        }
        isTimerActive = true;
        timerThread = new Thread(() -> {
            try {
                while (isTimerActive) {
                    action.exec();
                    Thread.sleep(intervalMs);
                }
            } catch (InterruptedException e) {
                System.out.printf("Timer is shutdown: %s, %s\n", e.getMessage(), e.getStackTrace());
            } finally {
                timerThread = null;
                isTimerActive = false;
            }
        });
        timerThread.start();
    }

    public void stop() {
        isTimerActive = false;
    }

    public void interrupt() {
        if (timerThread != null) {
            timerThread.interrupt();
            timerThread = null;
        }
        isTimerActive = false;
    }

    public int getIntervalMs() {
        return intervalMs;
    }

    public void setIntervalMs(int intervalMs) {
        this.intervalMs = intervalMs;
    }
}

