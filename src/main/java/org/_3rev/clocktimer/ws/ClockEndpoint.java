package org._3rev.clocktimer.ws;

import org._3rev.clocktimer.controller.ClockController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClockEndpoint {

    private ClockController clockController;

    public ClockEndpoint(ClockController clockController) {
        this.clockController = clockController;
    }

    @RequestMapping("/start")
    public void startTimer(@RequestParam(value = "seconds") int seconds) {
        clockController.startTimer(seconds);
    }

    @RequestMapping("/pause")
    public void pauseTimer() {
        clockController.pause();
    }

    @RequestMapping("/continue")
    public void continueTimer() {
        clockController.unpause();
    }
}
