package com.leetcode2.org.mathTools;
public class getMaxandMin {
    public static void main(String[] args) {
        int num1 = 12;
        int num2 = 10;
        int min = Math.min(num2, num1);
        int result=1;
        for (int i = 1; i <=min ; i++) {
            if (num2 %i==0 && num1%i==0) {
//                System.out.println(i);
                result=i;
            }
        }
        System.out.println("最大公约数是"+result);

       int resultMax=0;
       int max= Math.max(num1,num2);
        for (int i = num1*num2; i >=max ; i--) {
            if (i%num1==0 && i%num2==0){
                resultMax=i;
            }
        }
        System.out.println("最小公倍数"+resultMax);
    }
}
