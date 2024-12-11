package com.jinglinling.acm;

import java.util.Scanner;

/*
* ACM输入模板（多个数之和）
* */
public class MainTemplate{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasnext()){
            //读取输入元素个数
            int n = scanner.nextInt();

            //读取数组
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }

            int sum = 0;
            for (int num : arr) {
                sum += num;
            }

            System.out.println("Sum: " + sum);
        }

        scanner.close();
    }
}