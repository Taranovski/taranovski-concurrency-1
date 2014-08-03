/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.concurrent.task1;

/**
 *
 * @author Alyx
 */
public class CalculationPart extends Thread {

    public static final double step = 0.0001;

    Function function;
    double result = 0;

    double start;
    double end;

    /**
     *
     * @param function
     * @param start
     * @param end
     */
    public CalculationPart(Function function, double start, double end) {
        this.function = function;
        this.start = start;
        this.end = end;
    }

    /**
     *
     */
    @Override
    public void run() {
        for (double current = start; current < end; current += step) {
            result += function.calculate(current);
        }
    }
    
    public double getResult() {
        return result;
    }

}
