package com.leetcode2.org.mathTools;


public class fordPage {
    public static void main(String[] args) {
        double pageThickness =0.1;
            double qomolangmaHeight= 8848.86*1000;
            int  foldingTimes=0;
//            while (pageThickness<=qomolangmaHeight){
//                pageThickness*=2;
//                foldingTimes++;
//                System.out.println(pageThickness);
//            }

        do {
            pageThickness *=2;
            foldingTimes++;
            System.out.println(pageThickness);
        }while(pageThickness<=qomolangmaHeight);
        System.out.println("折叠次数为"+foldingTimes);
    }
}
