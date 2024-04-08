package com.cgvsu.protocurvefxapp;

public class BezierCurve {
    public BezierCurve()
    {
//        CreateFactorialTable();
    }

    // just check if n is appropriate, then return the result
    public double factorial(int input) {
        int x, fact = 1;
        for (x = input; x > 1; x--)
            fact *= x;
        return fact;
    }

    private double Ni(int n, int i)
    {
        double ni;
        double a1 = factorial(n);
        double a2 = factorial(i);
        double a3 = factorial(n - i);
        ni =  a1/ (a2 * a3);
        return ni;
    }

    // Calculate Bernstein basis
    private double Bernstein(int n, int i, double t)
    {
        double basis;
        double ti; /* t^i */
        double tni; /* (1 - t)^i */

        /* Prevent problems with pow */

        if (t == 0.0 && i == 0)
            ti = 1.0;
        else
            ti = Math.pow(t, i);

        if (n == i && t == 1.0)
            tni = 1.0;
        else
            tni = Math.pow((1 - t), (n - i));

        //Bernstein basis
        basis = Ni(n, i) * ti * tni;
        return basis;
    }

    public void Bezier2D(double[] b, int cpts, double[] p)
    {
        int npts = (b.length) / 2;
        int icount, jcount;
        //double step, t;

        // Calculate points on curve

        icount = 0;
        double t = 0;
        double step = 1.0 / (cpts - 1);

        for (int i1 = 0; i1 != cpts; i1++)
        {
            if ((1.0 - t) < 5e-6)
                t = 1.0;

            jcount = 0;
            p[icount] = 0.0;
            p[icount + 1] = 0.0;
            for (int i = 0; i != npts; i++)
            {
                double basis = Bernstein(npts - 1, i, t);
                p[icount] += basis * b[jcount];
                p[icount + 1] += basis * b[jcount + 1];
                jcount = jcount +2;
            }

            icount += 2;
            t += step;
        }
    }
}
