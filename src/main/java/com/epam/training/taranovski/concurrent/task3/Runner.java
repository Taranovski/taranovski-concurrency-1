/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.concurrent.task3;

/**
 *
 * @author user
 */
public class Runner {

    private Runner() {
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        MyCircleBuffer<Integer> buffer = new MyCircleBuffer<>(40);
        Consumer<Integer> consumer = new Consumer(buffer);
        Producer<Integer> producer = new Producer(buffer, new MyItemGenerator());

        new Thread(producer, "1producer1").start();
        new Thread(producer, "1producer2").start();
        new Thread(producer, "1producer3").start();
        new Thread(producer, "1producer4").start();
        new Thread(producer, "1producer5").start();

        new Thread(consumer, "1consumer1").start();
        new Thread(consumer, "1consumer2").start();
        new Thread(consumer, "1consumer3").start();
        new Thread(consumer, "1consumer4").start();
        new Thread(consumer, "1consumer5").start();
        new Thread(consumer, "1consumer6").start();
    }

}
