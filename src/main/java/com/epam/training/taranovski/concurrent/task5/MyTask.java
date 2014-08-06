/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.concurrent.task5;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class MyTask implements MyITask {

    private Random random = new Random();
    private int number;
    private int delay;
    private boolean done = false;

    @Override
    public void doWork() {
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadPoolRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
        number = random.nextInt(1000);
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

    /**
     *
     * @return
     */
    @Override
    public int getResult() {
        return number;
    }

}
