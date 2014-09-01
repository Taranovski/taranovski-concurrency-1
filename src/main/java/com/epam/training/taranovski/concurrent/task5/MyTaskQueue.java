/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.concurrent.task5;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author Alyx
 */
public class MyTaskQueue {

    private Queue<MyITask> queue;

    /**
     *
     */
    public MyTaskQueue() {
        queue = new ConcurrentLinkedQueue<>();
    }

    /**
     *
     * @param task
     */
    public void addTask(MyITask task) {
        queue.add(task);
    }

    /**
     *
     * @return
     */
    public MyITask getTask() {
        return queue.poll();
    }

}
