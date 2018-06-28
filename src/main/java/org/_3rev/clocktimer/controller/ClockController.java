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
    private ScheduledExecutorService executorService;
    private ClockTask clockTask;

    public ClockController(Clock clock) {
        this.clock = clock;
    }

    public void startTimer(int seconds) {
        if (executorService != null) {
            executorService.shutdown();
        }
        executorService = Executors.newSingleThreadScheduledExecutor();
        clockTask = new ClockTask(executorService, seconds);
        executorService.scheduleAtFixedRate(clockTask, 0, 1, TimeUnit.SECONDS);
    }

    public void pause() {
        this.clockTask.pause();
    }

    public void unpause() {
        this.clockTask.unpause();
    }

    class ClockTask extends TimerTask {

        private ScheduledExecutorService executorService;
        private boolean isPaused = false;
        private int secondsLeft;

        ClockTask(ScheduledExecutorService executorService, int secondsLeft) {
            this.executorService = executorService;
            this.secondsLeft = secondsLeft;
        }

        @Override
        public void run() {
            if (!isPaused) {
                clock.setTimeRemaining(secondsLeft--);
                if (secondsLeft < 0) {
                    clock.setText("Good Curling!");
                    executorService.shutdown();
                }
            }
        }

        void pause() {
            isPaused = true;
        }

        void unpause() {
            isPaused = false;
        }
    }
}
