package com.acplmaverick;

public class Main {

    private static void pythagorean_triplets_naive()
    {
        // If the sum of three numbers, a, b and c must equal 1000, then for each of them we may iterate from 1 to 1000.
        for(int i = 1; i <= 1000; ++i)
        {
            for(int j = 1; j <= 1000; ++j)
            {
                for(int k = 1; k <= 1000; ++k)
                {
                    Triplet triplet = new Triplet(i, j, k);

                    if(triplet.is_pythagorean())
                    {
                        if(triplet.is_end_condition())
                        {
                            triplet.print();
                            return;
                        }
                    }
                }
            }
        }

        // If we reach this point in code, it means that the triplet was not found.
        System.out.println("Error. Pythagorean triplet not found.");
    }

    public static void main(String[] args)
    {
        pythagorean_triplets_naive();
    }
}
