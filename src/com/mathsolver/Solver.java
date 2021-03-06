package com.mathsolver;

import java.lang.*;

public class Solver
{
    public static double Solve(String in)
    {
        int i = in.indexOf('(');
        while(i != -1)
        {
            i = in.indexOf('(');
            int j = findRespondingBracket(in);
            if (i != -1)
            {
                if (j != -1)
                {
                    double result = Solve(in.substring(i + 1, j));
                    in = in.substring(0, i) + result + in.substring(j + 1);
                } else throw new IllegalArgumentException("There is no closing brackets");
            }
        }


        i = in.indexOf('+');
        if (i != -1) return Solve(in.substring(0, i)) + Solve(in.substring(i + 1));

        i = in.indexOf('-');
        if (i != -1) return Solve(in.substring(0, i)) - Solve(in.substring(i + 1));

        i = in.indexOf('*');
        if (i != -1) return Solve(in.substring(0, i)) * Solve(in.substring(i + 1));

        i = in.indexOf('/');
        if (i != -1) return Solve(in.substring(0, i)) / Solve(in.substring(i + 1));

        i = in.indexOf('^');
        if (i != -1) return StrictMath.pow(Solve(in.substring(0, i)), Solve(in.substring(i + 1)));

        return Double.parseDouble(in); //in case of exception, it will return it
    }

    private static int findRespondingBracket(String in)
    {
        int n = 0;
        for (int i = 0; i < in.length(); i++)
        {
            if (in.charAt(i) == '(') n++;
            else if (in.charAt(i) == ')' && n > 1) n--;
            else if (in.charAt(i) == ')' && n == 1) return i;
        }
        return -1;
    }
}
