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
public class Producer<T> implements Runnable {

    private final MyCircleBuffer<T> buffer;
    private T item;
    private final MyItemGenerator<T> generator;
    private volatile boolean running = true;

    /**
     *
     * @param buffer
     * @param generator
     */
    public Producer(MyCircleBuffer<T> buffer, MyItemGenerator<T> generator) {
        this.buffer = buffer;
        this.generator = generator;
    }

    /**
     *
     */
    @Override
    public void run() {
        while (running) {
            item = generator.generate();
            buffer.put(item);
        }
    }

    /**
     *
     */
    public void stopMyThread() {
        running = false;
    }

}
