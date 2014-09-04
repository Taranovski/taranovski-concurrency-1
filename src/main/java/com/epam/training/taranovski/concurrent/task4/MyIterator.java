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
public class MyIterator {

    private volatile int current;
    private final int size;

    /**
     *
     * @param size
     */
    public MyIterator(int size) {
        current = 0;
        this.size = size;
    }

    /**
     *
     */
    public synchronized void goNext() {
        current = (current + 1) % size;
    }

    /**
     *
     * @return
     */
    public synchronized int get() {
        return current;
    }

}
