package com.acplmaverick;

import java.util.ArrayList;

public class Main {

    // ++ Pro implementation.

    /*
    Solution is more thoroughly described here:
    https://www.xarg.org/puzzle/project-euler/problem-4/

    First observation is that the number must be between 100^2 and 999^2
    or in the range of [10000,998001][10000, 998001][10000,998001].
    As the majority of numbers has 6 digits and we're looking for the largest, we ignore 5 digits numbers.
    Based on this, we can construct a palindromic number as:

    ′abccba′    = 100000a + 10000b + 1000c + 100c + 10b + a
                = 100001a + 10010b + 1100c
                = 11(9091a + 910b + 100c)

    As such, we're looking for two largest numbers
    p,q ∈ {x∣100 ≤ x ≤ 999} ⊂ N with:
    p*q = 11(9091a + 910b + 100c) ≤ 999^2

    This equation shows us, that either p or q, but not both must have a factor of 11.
     */

    private static boolean pro_is_palindromic(int number)
    {
        var reversedNumber = 0;
        final var originalNumber = number;

        while(number > 0)
        {
            // A simpler implementation. To compute reversedNumber each step of the loop we simply
            // a) add number % 10 - which yields last digit of the number
            // b) multiply reversedNumber by 10, "advancing" its digits by one magnitude level
            // c) divide number by ten to obtain the next digit in the next loop step

            reversedNumber = reversedNumber * 10 + number % 10;
            number = number / 10;
        }

        // A number is a palindromic number if this comparison yields true.
        return reversedNumber == originalNumber;
    }

    private static int compute_palindrome_pro()
    {
        int palindrome = 0;

        // So, we know that one of the numbers must be divisible by 11.
        // We take p as divisible by 11 so we iterate from the maximum possible multiplication of 11 (990)
        // to the minimum possible with the step of 11 - this way we check only multiplications of 11.
        for(int p = 990; p > 99; p -= 11)
        {
            // We can make no such assumptions for q, so we need to iterate through full range [999;100].
            // However, we make a few useful optimizations inside the loop.
            for(int q = 999; q > 99; --q)
            {
                // Compute the multiplication of two three-digit numbers.
                final int number = p * q;
                // If number is a palindrome AND is bigger than one stored in "palindrome" variable - assign it.
                if(palindrome < number && pro_is_palindromic(number))
                {
                    palindrome = number;
                    break;  // We iterate descending, so there won't be higher results for this p.
                            // We can break from the inner loop safely.
                }
                else if(number < palindrome)
                {
                    // If the result of the multiplication is smaller than the one stored in "palindrome" variable
                    // We can safely assume that there will not be any bigger for this p (same reason as above).
                    // We can break from the inner loop safely.
                    break;
                }
            }
        }

        return palindrome;
    }

    // -- Pro implementation.

    // ****************************************************************

    // ++ Naive implementation.

    private static boolean naive_is_palindromic(int number)
    {
        final ArrayList<Integer> digits = naive_get_digits_from_decimal_number(number);
        return naive_are_digits_palindromic(digits);
    }

    private static boolean naive_are_digits_palindromic(ArrayList<Integer> digits)
    {
        // Some pre-computation checks.
        if(digits == null || digits.size() % 2 != 0)
        {
            return false;
        }

        // We iterate from zero to the half of number of digits, each time we take digit at index (i) and (size - i - 1)
        // And compare them. All comparisons should evaluate to true if the number is palindromic.
        final int half_digit_num = digits.size() / 2;
        final int last_index = digits.size() - 1;
        for(int i = 0; i < half_digit_num; ++i)
        {
            final int digit_l = digits.get(i);
            final int digit_r = digits.get(last_index - i);

            if(digit_l != digit_r)
            {
                return false;
            }
        }

        return true;
    }

    private static ArrayList<Integer> naive_get_digits_from_decimal_number(int number)
    {
        ArrayList<Integer> digits = new ArrayList<>();
        int divisor = 10;

        while(number != 0)
        {
            final int digit = number % divisor;
            number /= divisor;
            digits.add(digit);
        }

        // We don't actually have to do it, because we're looking for a palindrome anyway.
        //Collections.reverse(digits);

        return digits;
    }

    private static int compute_palindrome_naive(final int minValue, final int maxValue)
    {
        // We iterate from maximum possible values in descending order.
        // This way we will find the desired palindrome faster.
        // However we won't account for duplicates, i.e. i == j and j == i should be computed only once,
        // but they will be computed twice.

        int max_palindrome = -1;

        for(int i = maxValue; i >= minValue; --i)
        {
            for(int j = maxValue; j >= i /*Optimization - we don't actually need to go below i*/; --j)
            {
                final int number = i * j;
                if(naive_is_palindromic(number))
                {
                    if(number > max_palindrome)
                    {
                        max_palindrome = number;
                    }
                }
            }
        }

        return max_palindrome;
    }

    // -- Naive implementation.

    // ****************************************************************

    public static void main(String[] args)
    {
        // Giving 100 and 999 as min and max, we limit search only to six-digit numbers.
        //final int palindrome = compute_palindrome_naive(100, 999);

        final int palindrome = compute_palindrome_pro();

        System.out.printf("Largest three-digit palindrome: %d%n", palindrome);
    }
}
