package com.acplmaverick;

public class Vector
{
    public Vector(int numComponents)
    {
        components = new float[numComponents];
    }

    public int get_num_components()
    {
        return components.length;
    }

    public void set_component(int index, float value)
    {
        if(index >= get_num_components())
        {
            return;
        }

        components[index] = value;
    }

    public float get_component(int index)
    {
        if(index >= get_num_components())
        {
            return 0.0f;
        }

        return components[index];
    }

    public static float compute_dot_product(Vector v_a, Vector v_b)
    {
        if(v_a.get_num_components() != v_b.get_num_components())
        {
            System.out.println("ERROR: compute_scalar_product: Vectors have different number of components.");
            return 0.0f;
        }

        float product = 0.0f;
        for(int i = 0; i < v_a.get_num_components(); ++i)
        {
            float component_a = v_a.get_component(i);
            float component_b = v_b.get_component(i);

            product += component_a * component_b;
        }

        return product;
    }

    private float[] components;
}
