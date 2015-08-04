package controller;

import javax.swing.*;

/**
 * @author Benedikt Zoennchen
 */
public class Runner implements Runnable {

    private boolean running;
    private boolean paused;
    private Envolver envolver;
    private JFrame frame;

    public Runner(final Envolver envolver, final JFrame frame) {
        this.frame = frame;
        this.envolver = envolver;
        running = false;
        paused = false;
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

    public void kill() {
        running = false;
    }

    public void start() {
        if(running == true) {
            paused = false;
        }
        else {
            running = true;
            new Thread(this).start();
        }
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!paused) {
                envolver.evolve();
                frame.repaint();
            }
        }
    }
}
