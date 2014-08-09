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

    Function function;
    double result = 0;

    double start;
    double end;
    double step;

    /**
     *
     * @param function
     * @param start
     * @param end
     * @param step
     */
    public CalculationPart(Function function, double start, double end, double step) {
        this.function = function;
        this.start = start;
        this.end = end;
        this.step = step;
    }

    /**
     *
     */
    @Override
    public void run() {
        for (double current = start; current < end; current += step) {
            result = result + function.calculate(current) + function.calculate(-current);
        }
    }

    public double getResult() {
        return result;
    }

}
