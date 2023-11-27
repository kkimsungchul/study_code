package com.sungchul;

import java.util.Arrays;


public class CodeTest004 {

    public static void main(String[] args) {
        int[] arr = {20, 8, 10, 1, 4, 15};
        int result = maxDifference(arr);
        System.out.println("최대값: " + result);
    }

    public static int maxDifference(int[] arr) {
        Arrays.sort(arr); // 배열을 정렬하여 최대값과 최소값 찾기

        int[] rearrangedArray = new int[arr.length];
        int left = 0;
        int right = arr.length - 1;

        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                rearrangedArray[i] = arr[left++]; // 최소값 채우기
            } else {
                rearrangedArray[i] = arr[right--]; // 최대값 채우기
            }
        }

        int maxDiff = 0;
        for (int i = 0; i < rearrangedArray.length - 1; i++) {
            maxDiff += Math.abs(rearrangedArray[i] - rearrangedArray[i + 1]);
        }

        return maxDiff;
    }
}
