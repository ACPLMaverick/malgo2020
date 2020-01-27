package com.acplmaverick;

public class Main {

    private static void pythagorean_triplets_pro()
    {
        // https://www.mathblog.dk/pythagorean-triplets/
        // pozdro poćwicz.
    }

    private static void pythagorean_triplets_naive()
    {
        final int sum = 1000;
        // We could use the facts that a < b < c, and thus exploit that a < sum/3, and a < b < sum/2.
        // This is valid because sum is also a + b + c.

        for(int i = 1; i <= sum / 3; ++i)
        {
            for(int j = 1; j <= sum / 2; ++j)
            {
                Triplet triplet = new Triplet(i, j);    // This triplet automatically satisfies the end condition.

                if(triplet.is_pythagorean())
                {
                    triplet.print();
                    return;
                }
            }
        }

        // If we reach this point in code, it means that the triplet was not found.
        System.out.println("Error. Pythagorean triplet not found.");
    }

    private static void pythagorean_triplets_very_naive()
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
        //pythagorean_triplets_very_naive();
        pythagorean_triplets_naive();
        //pythagorean_triplets_pro();
    }
}
