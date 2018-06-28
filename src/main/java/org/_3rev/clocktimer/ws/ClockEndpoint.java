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
    public void sayHello(@RequestParam(value = "seconds") int seconds) throws InterruptedException {
        clockController.startTimer(seconds);
    }
}
