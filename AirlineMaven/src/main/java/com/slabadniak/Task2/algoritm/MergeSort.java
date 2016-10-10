package com.slabadniak.task2.algoritm;

import com.slabadniak.task2.plane.Plane;

import java.util.ArrayList;
import java.util.Comparator;

public class MergeSort {
    private static Comparator<Plane> comparator;

    private MergeSort() {
    }

    public static void sort(ArrayList<Plane> planes, Comparator<Plane> comparator) {
        MergeSort.comparator = comparator;
        partion(planes, 0, planes.size() - 1);
    }

    public static void partion(ArrayList<Plane> planes, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        partion(planes, lo, mid);
        partion(planes, mid + 1, hi);
        merge(planes, lo, mid, hi);
    }

    private static void merge(ArrayList<Plane> planes, int lo, int mid, int hi) {
        ArrayList<Plane> copyPlanes = new ArrayList<>(planes);

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) planes.set(k, copyPlanes.get(j++));
            else if (j > hi) planes.set(k, copyPlanes.get(i++));
            else if (less(copyPlanes.get(j), copyPlanes.get(i))) planes.set(k, copyPlanes.get(j++));
            else planes.set(k, copyPlanes.get(i++));
        }
    }

    private static boolean less(Plane v, Plane w) {
        return comparator.compare(v, w) == -1;
    }
}
