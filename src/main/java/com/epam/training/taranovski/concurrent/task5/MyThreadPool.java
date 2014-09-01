/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.concurrent.task5;

import java.util.List;

/**
 *
 * @author Alyx
 */
public class MyThreadPool {

    private final MyTaskQueue queue;
    private final int threadCount;
    private final MyThread[] threadPool;

    /**
     *
     * @param threadCount
     */
    public MyThreadPool(int threadCount) {
        this.threadCount = threadCount;
        queue = new MyTaskQueue();
        threadPool = new MyThread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threadPool[i] = new MyThread(queue);
        }
    }

    /**
     *
     */
    public void start() {
        for (int i = 0; i < threadCount; i++) {
            threadPool[i].start();
        }
    }

    /**
     *
     */
    public void stop() {
        System.out.println("stopping thread pool...");
        for (MyThread threadPool1 : threadPool) {
            threadPool1.stopThread();
        }
    }

    /**
     *
     * @param task
     */
    public void submit(MyITask task) {
        queue.addTask(task);
    }

    /**
     *
     * @param list
     */
    public void submitAll(List<MyITask> list) {
        for (MyITask task : list) {
            queue.addTask(task);
        }
    }

    /**
     *
     * @param list
     */
    public void submitAll(MyITask[] list) {
        for (MyITask task : list) {
            queue.addTask(task);
        }
    }

}
