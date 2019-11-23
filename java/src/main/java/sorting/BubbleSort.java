package main.java.sorting;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(bubbleSort(new int[]{5, 10, 25, 1, 9, 8, 15, 30, 28})));
    }

    private static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return arr;
        }
        for (int i = 0; i < arr.length; ++i) { // 用于记录每一轮排序之后，已经确定位置的元素个数
            for (int j = 0; j < arr.length - i - 1; ++j) { // 注意arr.length - i - 1，因为j从0开始
                if (arr[j + 1] < arr[j]) { // 从小到大
                    arr[j + 1] = arr[j + 1] + arr[j];
                    arr[j] = arr[j + 1] - arr[j];
                    arr[j + 1] = arr[j + 1] - arr[j];
                }
            }
        }
        return arr;
    }
}
