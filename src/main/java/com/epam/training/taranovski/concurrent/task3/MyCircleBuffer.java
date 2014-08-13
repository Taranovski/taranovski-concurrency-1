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
public class MyCircleBuffer<T> {

    private volatile int currentRead;
    private volatile int currentWrite;

    private int size;
    private Object[] buffer;

    private final Object writeLock = new Object();
    private final Object readLock = new Object();

    /**
     *
     * @param size
     */
    public MyCircleBuffer(int size) {
        this.size = size;
        currentRead = 0;
        currentWrite = 0;
        buffer = new Object[size];
    }

    /**
     *
     * @param item
     */
    public void put(T item) {
        if (item == null) {
            throw new RuntimeException("cannot put a null into a buffer");
        }
        synchronized (writeLock) {
            while (buffer[currentWrite] != null) {
                Thread.yield();
            }
            buffer[currentWrite] = item;
            currentWrite = (currentWrite + 1) % size;
        }
    }

    /**
     *
     * @return
     */
    public T get() {
        synchronized (readLock) {
            while (buffer[currentRead] == null) {
                Thread.yield();
            }
            T item = (T) buffer[currentRead];
            buffer[currentRead] = null;
            currentRead = (currentRead + 1) % size;
            return item;
        }
    }
}
