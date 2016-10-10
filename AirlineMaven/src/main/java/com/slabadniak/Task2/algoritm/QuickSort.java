package com.slabadniak.task2.algoritm;

import com.slabadniak.task2.plane.Plane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class QuickSort {
    private static Comparator<Plane> comparator;

    private QuickSort() {
    }

    public static void sort(ArrayList<Plane> planes, Comparator<Plane> comparator) {
        QuickSort.comparator = comparator;
        Collections.shuffle(planes);
        sort(planes, 0, planes.size() - 1);
    }

    private static void sort(ArrayList<Plane> planes, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(planes, lo, hi);
        sort(planes, lo, j - 1);
        sort(planes, j + 1, hi);
    }

    private static int partition(ArrayList<Plane> planes, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Plane v = planes.get(lo);

        while (true) {
            while (less(planes.get(++i), v))
                if (i == hi) break;
            while (less(v, planes.get(--j)))
                if (j == lo) break;
            if (i >= j) break;
            exch(planes, i, j);
        }

        exch(planes, lo, j);
        return j;
    }

    private static boolean less(Plane v, Plane w) {
        return comparator.compare(v, w) == -1;
    }

    private static void exch(ArrayList planes, int i, int j) {
        Collections.swap(planes, i, j);
    }
}
