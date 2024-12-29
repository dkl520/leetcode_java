package com.leetcode2.org.大厂;

import java.io.*;

import static java.util.stream.Collectors.joining;

class Result1 {
    /*
     * Complete the 'maxElement' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER maxSum
     *  3. INTEGER k
     */

    public static int maxElement(int n, int maxSum, int k) {
        int left = 1, right = maxSum;
        int index = k;

        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (getSum(index, mid, n) <= maxSum) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private static long getSum(int index, int value, int n) {
        long count = 0;

        // On index's left:
        if (value > index) {
            count += (long)(value + value - index) * (index + 1) / 2;
        } else {
            count += (long)(value + 1) * value / 2 + index - value + 1;
        }

        // On index's right:
        if (value >= n - index) {
            count += (long)(value + value - n + 1 + index) * (n - index) / 2;
        } else {
            count += (long)(value + 1) * value / 2 + n - index - value;
        }

        return count - value;
    }
}

public class MaximizingElementwithConstraints {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());
        int maxSum = Integer.parseInt(bufferedReader.readLine().trim());
        int k = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result1.maxElement(n, maxSum, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

//LEETCODE 1802