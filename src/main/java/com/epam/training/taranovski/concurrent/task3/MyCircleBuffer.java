/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.concurrent.task3;

import java.util.logging.Level;
import java.util.logging.Logger;

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
        System.out.println(buffer[0]);
    }

    /**
     *
     * @param item
     */
    public synchronized void put(T item) {
        if (item == null) {
            throw new IllegalArgumentException("cannot put a null into a buffer");
        }

        while (buffer[currentWrite] != null) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(MyCircleBuffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        buffer[currentWrite] = item;
        currentWrite = (currentWrite + 1) % size;
        notifyAll();
    }

    /**
     *
     * @return
     */
    public synchronized T get() {

        while (buffer[currentRead] == null) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(MyCircleBuffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        T item = (T) buffer[currentRead];
        buffer[currentRead] = null;
        currentRead = (currentRead + 1) % size;
        notifyAll();
        return item;

    }
}
