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
     * binary search of an key in a list
     *
     * @param <T> an otem that is a comparable
     * @param <L> an list that is a list of T
     * @param key key to find
     * @param list a list to look
     * @param start an index of a list to look from
     * @param end an index of a list to look to
     * @return an index of item found
     */
    private static <T extends Comparable<T>, L extends List<T>> int binarySearch(T key,
            L list, int start, int end) {
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
     * parallell merge function. in this function a two sublists are merged into
     * one.
     *
     * @param <T> an otem that is a comparable
     * @param <L> an list that is a list of T
     * @param list1 a list from where to merge
     * @param start1 an index of start of sublist #1 in list1 to merge from
     * @param end1 an index of end of sublist #1 in list1 to merge to
     * @param start2 an index of start of sublist #2 in list1 to merge from
     * @param end2 an index of end of sublist #2 in list1 to merge to
     * @param list2 a list to store merged result to
     * @param start3 a start index in list2 from where to store result
     */
    private static <T extends Comparable<T>, L extends List<T>> void pMerge(L list1, int start1,
            int end1, int start2, int end2, L list2,
            int start3) {
        int n1 = end1 - start1 + 1;
        int n2 = end2 - start2 + 1;
        int temp;

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
            int q1 = (start1 + end1) / 2;
            int q2 = binarySearch(list1.get(q1), list1, start2, end2);
            int q3 = start3 + (q1 - start1) + (q2 - start2);
            list2.set(q3, list1.get(q1));

            final L list1f = list1;
            final int start1f = start1;
            final int q1f = q1;
            final int start2f = start2;
            final int q2f = q2;
            final L list2f = list2;
            final int start3f = start3;

            Thread fork = new Thread() {
                @Override
                public void run() {
                    pMerge(list1f, start1f, q1f - 1,
                            start2f, q2f - 1, list2f, start3f);
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
     * parallell merge sort. function takes one list and saves sort results to another
     *
     * @param <T> an otem that is a comparable
     * @param <L> an list that is a list of T
     * @param list1 a list to sort
     * @param start an index from where to apply sorting
     * @param end an index to where to apply sorting
     * @param list2 a list to save results to
     * @param start2 an index of list2 to start save results
     */
    public static <T extends Comparable<T>, L extends List<T>> void pMergeSort(L list1,
            int start, int end, L list2, int start2) {
        int n = end - start + 1;
        if (n == 1) {
            list2.set(start2, list1.get(start));
        } else {
            List<T> list3 = new ArrayList<>(list1);

            int q = (start + end) / 2;
            int q1 = q - start;

            final L list1f = list1;
            final int startf = start;
            final int qf = q;
            final List<T> list3f = list3;

            Thread fork = new Thread() {
                @Override
                public void run() {
                    pMergeSort(list1f, startf, qf, list3f, 0);
                }
            };
            fork.start();
            pMergeSort(list1, q + 1, end, list3, q1 + 1);
            try {
                fork.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(ParalellMergeSort.class.getName()).log(Level.SEVERE, null, ex);
            }
            pMerge(list3, 0, q1, q1 + 1, n - 1, list2, start2);

        }
    }

}
