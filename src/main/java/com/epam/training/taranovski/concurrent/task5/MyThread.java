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

    private static MyTaskQueue queue;
    private MyITask myITask;
    private static final int DEFAULT_SLEEP = 100;

    /**
     *
     */
    @Override
    public void run() {
        while (true) {
            myITask = queue.getTask();
            if (myITask == null) {
                try {
                    Thread.sleep(DEFAULT_SLEEP);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                myITask.doWork();
            }
        }
    }

    /**
     * @return the queue
     */
    public static MyTaskQueue getQueue() {
        return queue;
    }

    /**
     * @param aQueue the queue to set
     */
    public static void setQueue(MyTaskQueue aQueue) {
        queue = aQueue;
    }
}
