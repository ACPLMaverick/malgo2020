#include <iostream>
#include <cmath>

// Bisection method.
// Based on:
// https://www.tutorialspoint.com/cplusplus-program-for-bisection-method

float function(float x)
{
    // f(x) = 3 ∗ ln(0.5x + 1) + 3
    return 3 * logf(0.5f * x + 1.0f) + 3.0f;
}

int main(int argc, const char** argv)
{
    // Assuming that the function is continuous in a given interval <-2, 3>.
    // Also assuming that sign(f(x_min)) != sign(f(x_max)).
    // This means that there is an x where f(x) = 0 in this interval. 

    float x_min = -2.0f;
    float x_max = 3.0f;
    float middle = 0.0f;
    float val_middle = 0.0f;
    static const float eps = 0.0001f;
    
    do
    {
        middle = (x_min + x_max) * 0.5f;

        float val_min = function(x_min);
        float val_max = function(x_max);
        val_middle = function(middle);

        std::cout 
                << "Step : [" << x_min
                << ", " << middle
                << ", " << x_max << "]\n";

        if(val_min * val_middle < 0.0f)
        {
            // val_min and val_middle have different signs.
            // Solution is somewhere in between <val_min, val_middle>.
            x_max = middle;
        }
        else
        {
            // val_min and val_middle have the same signs.
            // This means val_middle and val_max should have different signs.
            // And the solution is somewhere in between <val_middle, val_max>.
            x_min = middle;
        }

    } while (fabsf(val_middle) > eps);
    
    std::cout << "Solution is x = " << middle << "\n";

    return 0;
}