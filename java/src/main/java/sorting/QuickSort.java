package main.java.sorting;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{15, 8, 9, 20, 1, 18, 3, 9, 11, 22};
        System.out.println(Arrays.toString(quickSort(arr, 0, arr.length - 1)));
    }

    private static int[] quickSort(int[] arr, int start, int end) {
        if (arr == null || start < 0 || end >= arr.length || start > end) {
            return null;
        }
        int partitionIndex = partition(arr, start, end);
        if (partitionIndex > start) {
            quickSort(arr, start, partitionIndex - 1);
        }
        if (partitionIndex < end) {
            quickSort(arr, partitionIndex + 1, end);
        }
        return arr;
    }

    // 算法参考geeksforgeeks
    private static int partition(int[] arr, int start, int end) {
        int pivot = (int) (start + Math.random() * (end - start + 1)); // 随机选择一个数作为对比点
        int biggerIndex = start - 1; // 挖坑，用比对比点小的数来填
        swap(arr, pivot, end); // 将对比点置于最后一位
        for (int i = start; i < end; ++i) {
            if (arr[i] >= arr[end]) {
                biggerIndex++;
                swap(arr, i, biggerIndex);
            }
        }
        biggerIndex++;
        swap(arr, biggerIndex, end); // 最后需要将对比点归位
        return biggerIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) { // 如果不加此条件，会出现置换0的情况
            return;
        }
        arr[j] = arr[j] + arr[i];
        arr[i] = arr[j] - arr[i];
        arr[j] = arr[j] - arr[i];
    }
}
