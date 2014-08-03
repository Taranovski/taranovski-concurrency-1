/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.concurrent.task4;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alyx
 */
public class MyThread extends Thread {

    private static Random random = new Random();
    private static final int SLEEP = 100;
    private static MyIterator iterator;

    int number;
    int maxDelay;
    MyObject myObj;

    /**
     *
     * @param number
     * @param maxDelay
     * @param myObj
     */
    public MyThread(int number, int maxDelay, MyObject myObj) {
        this.number = number;
        this.maxDelay = maxDelay;
        this.myObj = myObj;
    }

    /**
     *
     */
    @Override
    public void run() {
        while (true) {
            if (myObj.getObj() == null) {
                try {
                    Thread.sleep(SLEEP);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("My number is: " + number);
                System.out.println("working for " + random.nextInt(maxDelay) + " ms...");
                try {
                    Thread.sleep(maxDelay);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("work complete");
                iterator.goNext();
            }
        }
    }

    /**
     * @return the iterator
     */
    public static MyIterator getIterator() {
        return iterator;
    }

    /**
     * @param aIterator the iterator to set
     */
    public static void setIterator(MyIterator aIterator) {
        iterator = aIterator;
    }
}
