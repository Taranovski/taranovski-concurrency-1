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
public class GlobalRun {

    private GlobalRun() {
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        CircleThreadRun circleThreadRun = new CircleThreadRun(10);
        circleThreadRun.start();
        new Thread(new ExitListener(circleThreadRun)).start();
    }
}
