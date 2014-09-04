/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.concurrent.task4;

/**
 *
 * @author Alyx
 */
public class CircleThreadRun {

    private final int threadCount;
    private final MyThread[] myThreadArray;
    private final MyIterator myIterator;

    /**
     *
     * @param threadCount
     */
    public CircleThreadRun(int threadCount) {
        this.threadCount = threadCount;
        myIterator = new MyIterator(threadCount);
        myThreadArray = new MyThread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            myThreadArray[i] = new MyThread(i, myIterator);
        }

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
