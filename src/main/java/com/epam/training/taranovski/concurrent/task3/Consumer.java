/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.concurrent.task3;

/**
 *
 * @author user
 * @param <T>
 */
public class Consumer<T> implements Runnable {

    private final MyCircleBuffer<T> buffer;
    private T item;
    private volatile boolean running = true;

    /**
     *
     * @param buffer
     */
    public Consumer(MyCircleBuffer<T> buffer) {
        this.buffer = buffer;
    }

    /**
     *
     */
    @Override
    public void run() {
        while (running) {
            item = buffer.get();
        }
    }

    /**
     *
     */
    public void stopMyThread() {
        running = false;
    }
}
