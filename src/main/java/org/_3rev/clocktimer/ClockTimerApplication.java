package org._3rev.clocktimer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ClockTimerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ClockTimerApplication.class)
                .headless(false)
                .run(args);
    }
}
