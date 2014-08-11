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
    private Object[] locks;

    /**
     *
     * @param size
     */
    public MyCircleBuffer(int size) {
        this.size = size;
        currentRead = 0;
        currentWrite = 0;
        buffer = new Object[size];
        locks = new Object[size];
        for (int i = 0; i < size; i++) {
            locks[i] = new Object();
        }

    }

    /**
     *
     * @param item
     */
    public void put(T item) {
        if (item == null) {
            throw new RuntimeException("cannot put a null into a buffer");
        }
        int toLock = currentWrite;
        synchronized (locks[toLock]) {
            while (buffer[currentWrite] != null) {
                try {
                    locks[currentWrite].wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MyCircleBuffer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            buffer[currentWrite] = item;
            currentWrite = (currentWrite + 1) % size;
            locks[toLock].notifyAll();
        }
    }

    /**
     *
     * @return
     */
    public T get() {
        int toLock = currentRead;
        synchronized (locks[toLock]) {
            while (buffer[currentRead] == null) {
                try {
                    locks[currentRead].wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MyCircleBuffer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            T item = (T) buffer[currentRead];
            buffer[currentRead] = null;
            currentRead = (currentRead + 1) % size;
            locks[toLock].notifyAll();
            return item;
        }
    }
}
