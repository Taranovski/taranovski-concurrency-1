/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.concurrent.task2;

import java.util.List;

/**
 *
 * @author Alyx
 */
public class ParalellMergeSort1 implements Runnable {
    
    private final List<Comparable> list;
    private final int start;
    private final int end;
    private final int middle;
    
    private void merge(int start1, int end1, int start2, int end2) {
        int index1 = start1;
        int index2 = start2;
        
        for (int i = start1; i <= end2; i++) {
            if (list.get(index1).compareTo(list.get(index2)) < 0) {
                
            }
        }
    }
    
    

    /**
     *
     * @param initList
     * @param start
     * @param end
     * @param middle
     */
    public ParalellMergeSort1(List<Comparable> initList, int start, int end, int middle) {
        this.list = initList;
        this.start = start;
        this.end = end;
        this.middle = start + (end - start) / 2;
    }
    
    @Override
    public void run() {
        
    }
    
}
