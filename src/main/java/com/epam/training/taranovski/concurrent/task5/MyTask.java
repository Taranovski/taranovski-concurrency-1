/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.concurrent.task5;

import java.util.Random;

/**
 *
 * @author user
 */
public class MyTask implements MyITask {

    private final Random random = new Random();
    private int number;
    private boolean done = false;

    @Override
    public void doWork() {

        for (int i = 0; i < random.nextInt(10000000); i++) {
            number += random.nextInt(10000000);
        }

        done = true;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isDone() {
        return done;
    }

    /**
     *
     * @return
     */
    @Override
    public int getResult() {
        return number;
    }

}
