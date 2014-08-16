/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.concurrent.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Alyx
 */
public class SortRunner {

    private SortRunner() {
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int size = 4000;
        List<Integer> list1 = new ArrayList(size);
        List<Integer> list2 = new ArrayList(size);
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            list1.add(random.nextInt(size));
            list2.add(random.nextInt(size));
        }

        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);

        ParalellMergeSort.pMergeSort(list1, 0, list1.size() - 1, list2, 0);

        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);

    }
}
