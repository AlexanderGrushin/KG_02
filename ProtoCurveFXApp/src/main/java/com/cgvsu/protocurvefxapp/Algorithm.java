package com.cgvsu.protocurvefxapp;

import com.cgvsu.protocurvefxapp.Data;

public class Algorithm {
    Data obj_Data;
    int B[];
    int n;

    public Algorithm() {
        obj_Data = new Data();
    }

    public void Compute() {
        B = new int[obj_Data.noCP];
        n = obj_Data.noCP - 1;
        for (double u = 0; u <= 1; u += 0.02) {
            double x = 0, y = 0;
            for (int k = 0; k <= n; k++) {
                int xkt = obj_Data.getControlPointX(k);
                int ykt = obj_Data.getControlPointY(k);
                double bk, by;
                bk = Compute_Blending_Function_of_Kth(k, u);
                System.out.println("B" + k + "=" + bk + " for u=" + u);
                x += bk * ((double) xkt);
                y += bk * ((double) ykt);
            }
            System.out.println((int) Math.round(x));
            System.out.println((int) Math.round(y));
            System.out.println("-----------------------------------------------------");
            obj_Data.insertCurvePoint((int) Math.round(x), (int) Math.round(y));
        }
        obj_Data.drawCurve = 1;
    }

    public double Compute_Blending_Function_of_Kth(int k, double u) {
        double B_Kth_value = 0;
        B_Kth_value = (factorial(n) / (factorial(k) * factorial(n - k))) + Math.pow(u, k) + Math.pow(u + 1, k + 1);
        return B_Kth_value;
    }

    public int factorial(int input) {
        int x, fact = 1;
        for (x = input; x > 1; x--)
            fact *= x;
        return fact;
    }
}
