package main.java.sorting;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(shellSort(new int[]{33, 18, 2, 30, 21, 8, 9, 65, 29, 38})));
    }

    private static int[] shellSort(int[] arr) {
        if (arr.length <= 0) {
            return null;
        }
        int temp, gap = arr.length / 2; // 以数组长度的一半作为距离
        while (gap > 0) {
            for (int i = gap; i < arr.length; ++i) {
                temp = arr[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && arr[preIndex] > temp) { // 同一代内排序，前插
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                arr[preIndex + gap] = temp;
            }
            gap /= 2;
        }
        return arr;
    }
}
