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

    public static final double STEP = 0.0001;

    private int threadCount;
    private long executionTime = 0;

    private double start;
    private double end;
    private Function function;
    private double result = 0;
    private double tempStep;

    /**
     *
     * @param function
     * @param number
     * @param threadCount
     */
    public Calculation(Function function, double number, int threadCount) {
        this.threadCount = threadCount;

        if (number > 0) {
            this.start = -number;
            this.end = number;
        } else {
            this.start = number;
            this.end = -number;
        }

        this.function = function;
        this.tempStep = STEP * threadCount;
    }

    /**
     *
     * @return
     */
    public double execute() {

        CalculationPart[] array = new CalculationPart[threadCount];

        double temp = 0;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < threadCount; i++) {
            array[i] = new CalculationPart(function, start + temp, end, tempStep);
            temp += STEP;
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
        //result += function.calculate(end);

        executionTime = System.currentTimeMillis() - startTime;
        return result;
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

}
