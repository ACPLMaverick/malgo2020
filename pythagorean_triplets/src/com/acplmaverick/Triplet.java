package com.acplmaverick;

class Triplet
{
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

    // Constructor with arguments
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
        return (a + b + c) == 1000;
    }

    public void print()
    {
        System.out.println(String.format("a = [%d], b = [%d], c = [%d], abc = [%d]", a, b, c, a * b * c));
    }
}