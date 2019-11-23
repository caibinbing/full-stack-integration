package main.java.sorting;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(selectionSort(new int[]{8, 19, 22, 1, 38, 2, 56, 9, 10, 34})));
    }

    private static int[] selectionSort(int[] arr) {
        if (arr.length <= 0) {
            return arr;
        }
        for (int i = 0; i < arr.length; ++i) { // 用于控制位置
            int minIndex = i;
            for (int j = i + 1; j < arr.length; ++j) { // 每次选最小的替换第一位
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }
}
