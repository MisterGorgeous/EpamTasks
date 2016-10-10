package com.slabadniak.task2.algoritm;

import com.slabadniak.task2.plane.Plane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ShellSort {
    private static Comparator<Plane> comparator;

    private ShellSort() {
    }

    public static void sort(ArrayList<Plane> planes, Comparator<Plane> comparator) {
        ShellSort.comparator = comparator;
        int n = planes.size();
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(planes.get(j), planes.get(j - h)); j -= h) {
                    exch(planes, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    private static boolean less(Plane v, Plane w) {
        return comparator.compare(v, w) == -1;
    }

    private static void exch(ArrayList planes, int i, int j) {
        Collections.swap(planes, i, j);
    }

}
