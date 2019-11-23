package main.java.sorting;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(insertionSort(new int[]{2, 11, 28, 4, 81, 8, 20, 33, 15, 29})));
    }

    private static int[] insertionSort(int[] arr) {
        if (arr.length <= 0) {
            return arr;
        }
        int current;
        for (int i = 0; i < arr.length- 1; ++i) {
            current = arr[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < arr[preIndex]) { // 前插，所有数据后移
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current; // 插入
        }
        return arr;
    }
}
