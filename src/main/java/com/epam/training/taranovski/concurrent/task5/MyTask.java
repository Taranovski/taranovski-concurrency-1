/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.concurrent.task5;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class MyTask implements MyITask {

    private int number;
    private int delay;
    private boolean done = false;

    /**
     *
     * @param number
     * @param delay
     */
    public MyTask(int number, int delay) {
        this.number = number;
        this.delay = delay;
    }

    /**
     *
     */
    @Override
    public void doWork() {
        System.out.println("My number is: " + number);
        System.out.println("working for " + delay + " ms...");
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {
            Logger.getLogger(com.epam.training.taranovski.concurrent.task4.MyThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("work complete");
        done = true;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isDone() {
        return done;
    }

}
