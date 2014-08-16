/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.concurrent.task4;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alyx
 */
public class ExitListener implements Runnable {

    private CircleThreadRun circleThreadRun;
    private Scanner scanner = new Scanner(System.in);

    /**
     *
     * @param circleThreadRun
     */
    public ExitListener(CircleThreadRun circleThreadRun) {
        this.circleThreadRun = circleThreadRun;
    }

    @Override
    public void run() {
        while (true) {
            if (!"exit".equals(scanner.nextLine())) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ExitListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                circleThreadRun.stop();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ExitListener.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
    }

}
