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
public class ParalellMergeSort {

    /**
     *
     * @param key
     * @param list
     * @param start
     * @param end
     * @return
     */
    private static int binarySearch(Comparable key, List<Comparable> list, int start, int end) {
        int low = start;
        int high = Math.max(start, end + 1);
        int mid;
        while (low < high) {
            mid = (low + high) / 2;
            if (key.compareTo(list.get(mid)) <= 0) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }

    /**
     *
     * @param left
     * @param right
     * @param target
     */
    private static void pMerge(List<Comparable> list1, int start1, int end1, int start2, int end2, List<Comparable> list2, int start3) {
        int n1 = end1 - start1 + 1;
        int n2 = end2 - start2 + 1;
        int temp;
        int q1;
        int q2;
        int q3;
        if (n1 < n2) {
            temp = start2;
            start2 = start1;
            start1 = temp;

            temp = end2;
            end2 = end1;
            end1 = temp;

            temp = n2;
            n2 = n1;
            n1 = temp;
        }
        if (n1 != 0) {
            q1 = (start1 + end1) / 2;
            q2 = binarySearch(list1.get(q1), list1, start2, end2);
            q3 = start3 + (0);
        }

    }

}
