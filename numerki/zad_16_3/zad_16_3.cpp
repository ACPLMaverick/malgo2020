#include <iostream>
#include <iomanip>
#include <cmath>

double function(double x)
{
    return 2.0 * ((1.0 / (1.0 + x * x)) + (1.0 / (2.0 * x)) - (3.0 * x * x * x));
}

bool compare(double result, double result_analytic, double eps, int num_iterations, const char* method_name)
{
    const double error = fabs(fabs(result) - fabs(result_analytic));
    std::cout << "Method : [" << method_name << "], Result : [" << result << "], Error : ["
            << error << "], Num iterations : [" << num_iterations << "]\n";
    return error <= eps;
}

void integrate_rectangles(const double x_min, const double x_max, const double result_analytic,
    const double eps)
{
    // http://theflyingkeyboard.net/algorithms/midpoint-rule-rectangle-method-algorithm/
    // http://theflyingkeyboard.net/c/c-midpoint-rule-rectangle-method-2/

    double result = 0.0;
    int num_iterations = 1;
    bool is_exact = false;

    do
    {
        // Calculate sum(f(m_i))
        double sum = 0.0;
        const double length = x_max - x_min;
        for(int i = 1; i <= num_iterations; ++i)    // <1, n>
        {
            double m_i = x_min + ((2.0 * (double)i - 1.0) / (2.0 * (double)num_iterations)) * length;
            sum += function(m_i);
        }

        // Multiply by (b - a) / n
        result = (length / (double)num_iterations) * sum;

        is_exact = compare(result, result_analytic, eps, num_iterations, "Rectangles");
        ++num_iterations; // Might be faster to increase num_iterations non-linearly.
    } while (is_exact == false);
}

void integrate_simpson(const double x_min, const double x_max, const double result_analytic,
    const double eps)
{
    // https://eduinf.waw.pl/inf/alg/004_int/0004.php

    double result = 0.0;
    int num_iterations = 1;
    bool is_exact = false;

    do
    {
        const double length = x_max - x_min; // Length of section.
        const double result_0 = function(x_min);
        const double result_n = function(x_max);
        
        double sum_f = 0.0; // Sum of function values at the beginning of each section.
        double sum_t = 0.0; // Sum of function values at the middle of each section.

        for(int i = 1; i <= num_iterations; ++i)
        {
            const double x_i = x_min + ((double)i / (double)num_iterations) * length;
            const double x_i_min_1 = x_min + ((double)(i - 1) / (double)num_iterations) * length;
            const double t_i = (x_i_min_1 + x_i) / 2.0;

            sum_t += function(t_i);

            if(i == num_iterations)
            {
                continue;
            }

            sum_f += function(x_i);
        }

        // Combine sums with constant parameters.
        result = (length / (6.0 * (double)num_iterations)) * (result_0 + result_n + 2.0 * sum_f + 4.0 * sum_t);
        
        is_exact = compare(result, result_analytic, eps, num_iterations, "Simpson");
        ++num_iterations; // Might be faster to increase num_iterations non-linearly.
    } while (is_exact == false);
}

int main(int argc, const char** argv)
{
    static const double x_min = 1.0;
    static const double x_max = 5.0;
    static const double eps = 0.0001;
    static const double result_analytic = -933.21455688047076;

    std::cout << std::fixed << std::setprecision(15);   // Setting up cout.

    std::cout << "Analytic result : [" << result_analytic << "]\n";

    integrate_rectangles(x_min, x_max, result_analytic, eps);

    std::cout << "\n";

    integrate_simpson(x_min, x_max, result_analytic, eps);

    return 0;
}