/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.concurrent.task1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alyx
 */
public class Calculation {

    private int threadCount;
    private long executionTime = 0;

    private double start;
    private double end;
    private Function function;
    private double result = 0;

    private boolean ready = false;

    /**
     *
     * @param function
     * @param start
     * @param end
     * @param threadCount
     */
    public Calculation(Function function, double start, double end, int threadCount) {
        this.threadCount = threadCount;
        this.start = start;
        this.end = end;
        this.function = function;
    }

    /**
     *
     */
    public void execute() {
        double part = (end - start) / threadCount;

        CalculationPart[] array = new CalculationPart[threadCount];

        double temp = start;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < threadCount; i++) {
            array[i] = new CalculationPart(function, temp, temp + part);
            temp += part;
            array[i].start();
        }

        try {
            for (int i = 0; i < threadCount; i++) {
                array[i].join();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Calculation.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < threadCount; i++) {
            result += array[i].getResult();
        }

        executionTime = System.currentTimeMillis() - startTime;
        ready = true;

    }

    /**
     * @return the threadCount
     */
    public int getThreadCount() {
        return threadCount;
    }

    /**
     * @return the executionTime
     */
    public long getExecutionTime() {
        try {
            while (!ready) {
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {

        }

        return executionTime;
    }

    /**
     * @return the start
     */
    public double getStart() {
        return start;
    }

    /**
     * @return the end
     */
    public double getEnd() {
        return end;
    }

    /**
     * @return the function
     */
    public Function getFunction() {
        return function;
    }

    /**
     * @return the result
     */
    public double getResult() {
        try {
            while (!ready) {
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {

        }
        return result;
    }

}
