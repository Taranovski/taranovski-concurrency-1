/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.concurrent.task5;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alyx
 */
public class ThreadPoolRunner {

    private ThreadPoolRunner() {
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool(10);
        pool.start();

        MyITask[] taskList = new MyITask[50000];

        for (int i = 0; i < taskList.length; i++) {
            taskList[i] = new MyTask();
        }

        pool.submitAll(taskList);

        for (MyITask taskList1 : taskList) {
            while (true) {
                if (taskList1.isDone()) {
                    System.out.println("result for task " + taskList1 + " equals: " + taskList1.getResult());
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
