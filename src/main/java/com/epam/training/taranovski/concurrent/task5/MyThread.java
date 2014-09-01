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
 * @author Alyx
 */
public class MyThread extends Thread {

    private final MyTaskQueue queue;
    private MyITask myITask;
    private static final int DEFAULT_SLEEP = 100;
    private volatile boolean running = true;

    /**
     *
     * @param queue
     */
    public MyThread(MyTaskQueue queue) {
        super();
        this.queue = queue;
    }

    /**
     *
     */
    @Override
    public void run() {
        while (running) {
            myITask = queue.getTask();
            if (myITask == null) {
                try {
                    Thread.sleep(DEFAULT_SLEEP);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("pool worker " + Thread.currentThread().getName() + " is working on task " + myITask);
                myITask.doWork();
                System.out.println("pool worker " + Thread.currentThread().getName() + " done its job");
            }
        }
    }

    /**
     * @return the queue
     */
    public MyTaskQueue getQueue() {
        return queue;
    }

    /**
     *
     */
    public void stopThread() {
        running = false;
    }
}
