package com.acplmaverick;

class Triplet
{
    // Static const class variable
    private static final int SUM = 1000;

    // Class Members
    public final int a;
    public final int b;
    public final int c;

    // Default constructor (non-argument)
    public Triplet()
    {
        a = 0;
        b = 0;
        c = 0;
    }

    // Constructor with two arguments - uses the fact that a+b+c = 1000 to compute c automatically.
    public Triplet(int a_a, int a_b)
    {
        a = a_a;
        b = a_b;
        c = SUM - a - b;
    }

    // Constructor with three arguments
    public Triplet(int a_a, int a_b, int a_c)
    {
        a = a_a;
        b = a_b;
        c = a_c;
    }

    // Member functions (methods)
    public boolean is_pythagorean()
    {
        return (a * a + b * b) == (c * c);
    }

    public boolean is_end_condition()
    {
        return (a + b + c) == SUM;
    }

    public void print()
    {
        System.out.println(String.format("a = [%d], b = [%d], c = [%d], abc = [%d]", a, b, c, a * b * c));
    }
}