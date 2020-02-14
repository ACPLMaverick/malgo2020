package com.acplmaverick;

public class Main
{
    static void dot_product_test()
    {
        Vector v_a = new Vector(5);
        Vector v_b = new Vector(5);

        v_a.set_component(0, 1.0f);
        v_a.set_component(1, 2.0f);
        v_a.set_component(2, 3.0f);
        v_a.set_component(3, 4.0f);
        v_a.set_component(4, 5.0f);

        v_b.set_component(0, 15.0f);
        v_b.set_component(1, 13.0f);
        v_b.set_component(2, 11.0f);
        v_b.set_component(3, 9.0f);
        v_b.set_component(4, 7.0f);

        float dot_product = Vector.compute_dot_product(v_a, v_b);
        System.out.println(String.format("Test dot product is: %f", dot_product));
    }

    public static void main(String[] args)
    {
        dot_product_test();
    }
}
