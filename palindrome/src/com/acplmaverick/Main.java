package com.acplmaverick;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    // ++ Naive implementation.

    private static boolean naive_is_palindromic(ArrayList<Integer> digits)
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

    private static int compute_palindrome_naive(int maxValue)
    {
        // We iterate from maximum possible values in descending order.
        // This way we will find the desired palindrome faster.
        // However we won't account for duplicates, i.e. i == j and j == i should be computed only once,
        // but they will be computed twice.

        int max_palindrome = -1;

        for(int i = maxValue; i >= 1 /*No need to include zero.*/; --i)
        {
            for(int j = maxValue; j >= 1; --j)
            {
                final int number = i * j;
                final ArrayList<Integer> digits = naive_get_digits_from_decimal_number(number);
                if(naive_is_palindromic(digits))
                {
                    if(number > max_palindrome)
                    {
                        max_palindrome = number;
                    }
                }
            }
        }

        // Palindromic not found - code should not get here,
        // but we still need to return a value in all control paths.
        return max_palindrome;
    }

    // -- Naive implementation.

    public static void main(String[] args)
    {
        final int palindrome = compute_palindrome_naive(999);
        System.out.printf("Largest three-digit palindrome: %d%n", palindrome);
    }
}
