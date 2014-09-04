/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.concurrent.task4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alyx
 */
public class MyThread extends Thread {
    
    private final int number;
    private final MyIterator iterator;
    private boolean running = true;

    /**
     *
     * @param number
     * @param myObj
     */
    public MyThread(int number, MyIterator myObj) {
        this.number = number;
        this.iterator = myObj;
    }

    /**
     *
     */
    @Override
    public void run() {
        
        while (running) {
            
            if (iterator.get() == number) {
                System.out.println("My number is: " + number);
                iterator.goNext();
            } else {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    }

    /**
     * @return the iterator
     */
    public MyIterator getIterator() {
        return iterator;
    }

    /**
     *
     */
    public void stopMyThread() {
        running = false;
    }
}
