package org._3rev.clocktimer.view;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Component
public class Clock extends JFrame {

    private static final int SECONDS_IN_HOUR = 3600;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final String SEPARATOR = ":";

    private static final int MIN_FONT_SIZE = 3;
    private static final int MAX_FONT_SIZE = 500;

    private JPanel panel1;
    private JLabel clock;

    public Clock() throws HeadlessException {
        setContentPane(panel1);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
//        setUndecorated(true);
        pack();
        setVisible(true);
        this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adaptLabelFont(clock);
            }
        });
    }

    public void setTimeRemaining(int timeInSeconds) {
        int hours = timeInSeconds / SECONDS_IN_HOUR;
        int remainder = timeInSeconds - hours * SECONDS_IN_HOUR;
        int mins = remainder / SECONDS_IN_MINUTE;
        remainder = timeInSeconds - mins * SECONDS_IN_MINUTE;
        int seconds = remainder;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(timeToString(hours)).append(SEPARATOR).append(timeToString(mins)).
                append(SEPARATOR).append(timeToString(seconds));
        setText(stringBuilder.toString());
    }

    private String timeToString(int time) {
        String s = String.valueOf(time);
        if (s.length() < 2) {
            return "0" + s;
        } else {
            return s;
        }
    }

    public void setText(String text) {
        this.clock.setText(text);
        adaptLabelFont(clock);
    }

    private void adaptLabelFont(JLabel l) {
        Rectangle r = this.getBounds();
        int fontSize = MIN_FONT_SIZE;
        Font f = l.getFont();

        Rectangle r1 = new Rectangle();
        Rectangle r2 = new Rectangle();
        while (fontSize < MAX_FONT_SIZE) {
            r1.setSize(getTextSize(l, f.deriveFont(f.getStyle(), fontSize)));
            r2.setSize(getTextSize(l, f.deriveFont(f.getStyle(), fontSize + 1)));
            if (r.contains(this.getX(), this.getY(), r1.width, r1.height) && !r.contains(this.getX(), this.getY(), r2.width, r2.height)) {
                break;
            }
            fontSize++;
        }

        l.setFont(f.deriveFont(f.getStyle(), fontSize));
//        System.out.println(fontSize);
        repaint();
    }

    private Dimension getTextSize(JLabel l, Font f) {
        Dimension size = new Dimension();
        FontMetrics fm = l.getFontMetrics(f);
        size.width = fm.stringWidth(l.getText());
        size.height = fm.getHeight();

        return size;
    }
}
