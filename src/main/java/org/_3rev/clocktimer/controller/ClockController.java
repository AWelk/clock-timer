package org._3rev.clocktimer.controller;

import org._3rev.clocktimer.view.Clock;
import org.springframework.stereotype.Service;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class ClockController {

    private Clock clock;

    public ClockController(Clock clock) {
        this.clock = clock;
    }

    public void startTimer(int seconds) {

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        TimerTask task = new TimerTask() {
            int secondsLeft = seconds;

            @Override
            public void run() {
                clock.setTimeRemaining(secondsLeft--);
                if (secondsLeft < 0) {
                    clock.setText("Good Curling!");
                    executorService.shutdown();
                }
            }
        };
        executorService.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }
}
