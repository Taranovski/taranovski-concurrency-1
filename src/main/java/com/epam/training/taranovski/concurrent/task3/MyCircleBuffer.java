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
        int toLock = -1;
        synchronized (locks[toLock = currentWrite]) {
            while (buffer[toLock] != null) {
                try {
                    locks[toLock].wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MyCircleBuffer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            buffer[toLock] = item;
            currentWrite = (toLock + 1) % size;
            locks[toLock].notifyAll();
        }
    }

    /**
     *
     * @return
     */
    public T get() {
        int toLock = -1;
        synchronized (locks[toLock = currentRead]) {
            //toLock = currentRead;
            while (buffer[toLock] == null) {
                try {
                    locks[toLock].wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MyCircleBuffer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            T item = (T) buffer[toLock];
            buffer[toLock] = null;
            currentRead = (toLock + 1) % size;
            locks[toLock].notifyAll();
            return item;
        }
    }
}
