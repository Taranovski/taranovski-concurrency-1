/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.concurrent.task5;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alyx
 */
public class ThreadPoolRunner {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        final Random random = new Random();
        MyThreadPool pool = new MyThreadPool(10);
        pool.start();

        MyITask[] taskList = new MyITask[50];

        for (int i = 0; i < taskList.length; i++) {
            taskList[i] = new MyTask();
        }

        pool.submitAll(taskList);

        for (int i = 0; i < taskList.length; i++) {
            while (true) {
                if (taskList[i].isDone()) {
                    System.out.println("result for task " + taskList[i] + " equals: " + taskList[i].getResult());
                    break;
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadPoolRunner.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        MyITask task = new MyTask();
        pool.submit(task);

        while (!task.isDone()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadPoolRunner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("result for task " + task + " equals: " + task.getResult());

        pool.stop();

    }
}
