/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.concurrent.task5;

/**
 *
 * @author Alyx
 */
public interface MyITask {

    public void doWork();

    public boolean isDone();
    
    public int getResult();
}
