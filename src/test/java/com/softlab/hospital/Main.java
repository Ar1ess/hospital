package com.softlab.hospital;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r,y,g,n,k,t;
        long result = 0;
        r = sc.nextInt();
        y = sc.nextInt();
        g = sc.nextInt();
        n = sc.nextInt();
        int x = r+y+g;
        for(int i=0; i<n; i++) {
            k = sc.nextInt();
            t = sc.nextInt();
            long tmp = 0;
            if(0 == k) {
                result += t;
            } else {
                if (1 == k) {
                    tmp = (result+r-t)%x;
                } else if (2 == k) {
                    tmp = (result-t)%x;
                } else if (3 ==k) {
                    tmp = (result+r+g-t)%x;
                }
                if (tmp <= r) {
                    result += r-tmp;
                } else if(tmp > r+g) {
                    result += x-tmp+r;
                }
            }
        }
        System.out.println(result);
    }

}
