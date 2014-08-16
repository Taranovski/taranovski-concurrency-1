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

    private static final Random RANDOM = new Random();
    private static final int MIN_DELAY = 200;
    private static final int MAX_OFFSET = 800;

    private final int threadCount;

    private final MyObject[] myObjectArray;
    private final MyThread[] myThreadArray;

    private final MyIterator myIterator;

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
            myThreadArray[i] = new MyThread(i, MIN_DELAY + RANDOM.nextInt(MAX_OFFSET), myObjectArray[i]);
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
            myThreadArray[i].stopMyThread();
        }
    }

}
