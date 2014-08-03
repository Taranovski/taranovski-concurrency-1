/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.concurrent.task4;

import java.util.Random;

/**
 *
 * @author Alyx
 */
public class CircleThreadRun {

    private static Random random = new Random();
    private static final int minDelay = 200;
    private static final int maxOffset = 800;

    private int threadCount;

    private MyObject[] myObjectArray;
    private MyThread[] myThreadArray;

    private MyIterator myIterator;

    /**
     *
     * @param threadCount
     */
    public CircleThreadRun(int threadCount) {
        this.threadCount = threadCount;
        myObjectArray = new MyObject[threadCount];

        for (int i = 0; i < threadCount; i++) {
            myObjectArray[i] = new MyObject();
        }

        myThreadArray = new MyThread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            myThreadArray[i] = new MyThread(i, minDelay + random.nextInt(maxOffset), myObjectArray[i]);
        }

        myIterator = new MyIterator(myObjectArray);

        MyThread.setIterator(myIterator);
    }

    /**
     *
     */
    public void start() {
        for (int i = 0; i < threadCount; i++) {
            myThreadArray[i].start();
        }
    }

    /**
     *
     */
    public void stop() {
        for (int i = 0; i < threadCount; i++) {
            myThreadArray[i].interrupt();
        }
    }

}
