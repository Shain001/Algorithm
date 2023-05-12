package com.shain.dp;

public class Fibonacci_L509 {
    public int fib(int n) {
        if (n == 0)
            return 0;

        int n0 = 0;
        int n1 = 1;
        int fn = n1;
        for (int i = 1; i < n; i++) {
            fn = n0 + n1;
            n0 = n1;
            n1 = fn;
        }

        return fn;
    }
}
