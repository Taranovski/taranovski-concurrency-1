/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.concurrent.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private static int binarySearch(Integer key, List<Integer> list, int start, int end) {
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
    private static void pMerge(List<Integer> list1, int start1, int end1, int start2, int end2, List<Integer> list2, int start3) {
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
            q3 = start3 + (end1 - start1) + (end2 - start2);
            list2.set(q3, list1.get(q1));

            final List<Integer> list1f = list1;
            final int start1f = start1;
            final int q1f = q1;
            final int start2f = start2;
            final int q2f = q2;
            final List<Integer> list2f = list2;
            final int start3f = start3;

            Thread fork = new Thread() {
                @Override
                public void run() {
                    pMerge(list1f, start1f, q1f - 1, start2f, q2f - 1, list2f, start3f);
                }
            };
            fork.start();
            pMerge(list1, q1f + 1, end1, q2f, end2, list2f, q3 + 1);
            try {
                fork.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(ParalellMergeSort.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param list1
     * @param start
     * @param end
     * @param list2
     * @param start2
     */
    public static void pMergeSort(List<Integer> list1, int start, int end, List<Integer> list2, int start2) {
        int n = end - start + 1;
        if (n == 1) {
            list2.set(start2, list1.get(start));
        } else {
            List<Integer> list3 = new ArrayList(n);
            int q = (start + end) / 2;
            int q1 = q - start + 1;

            final List<Integer> list1f = list1;
            final int startf = start;
            final int qf = q;
            final List<Integer> list3f = list3;

            Thread fork = new Thread() {
                @Override
                public void run() {
                    pMergeSort(list1f, startf, qf, list3f, 0);
                }
            };
            fork.start();
            pMergeSort(list1, q + 1, end, list2, q1 + 1);
            try {
                fork.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(ParalellMergeSort.class.getName()).log(Level.SEVERE, null, ex);
            }
            pMerge(list3, 0, q1, q1 + 1, n, list2, start2);
        }
    }

}