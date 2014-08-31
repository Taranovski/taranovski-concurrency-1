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

    private static final Object TOKEN = new Object();
    private final MyObject[] myObjects;
    private int current;

    /**
     *
     * @param myObjects
     */
    public MyIterator(MyObject[] myObjects) {
        this.myObjects = myObjects;
        current = 0;
        myObjects[current].setObj(TOKEN);
    }

    /**
     *
     */
    public synchronized void goNext() {
        myObjects[current].setObj(null);
        current = (current + 1) % myObjects.length;
        myObjects[current].setObj(this);
    }

}
