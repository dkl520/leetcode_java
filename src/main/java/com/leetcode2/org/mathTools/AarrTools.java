package com.leetcode2.org.mathTools;
import java.util.Arrays;
public class AarrTools {
    public static void main(String[] args) {
        int [] arr2 = new int[]{1,2};
        int [] arr1= new int[] {1,2,0,5,3,68,9,90,0,-1,3};
//        System.out.println(arr2==arr1);
//        System.out.println(Arrays.equals(arr1,arr2));
//        System.out.println();
        Arrays.sort(arr1);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.binarySearch(arr1,0));
//        System.out.println();

        Arrays.fill(arr1,10);
        System.out.println(Arrays.toString(arr1));
        int hashCode = Arrays.hashCode(arr1);
        System.out.println(Arrays.hashCode(arr1));
        System.out.println(Arrays.hashCode(arr2));
        System.out.println(arr2[1]);
//        System.out.println(arr1.hashCode());
//        System.out.println(arr2.hashCode());





    }
}
